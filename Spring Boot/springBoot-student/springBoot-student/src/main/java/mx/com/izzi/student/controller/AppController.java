package mx.com.izzi.student.controller;

import lombok.extern.slf4j.Slf4j;
import mx.com.izzi.student.controller.in.ProductRequest;
import mx.com.izzi.student.controller.out.ProductResponse;
import mx.com.izzi.student.db.model.mapper.MapToReconcile;
import mx.com.izzi.student.db.model.service.ProductServiceImp;
import mx.com.izzi.student.db.model.repository.ProductDb;
import mx.com.izzi.student.file.FilenameGenerator;
import mx.com.izzi.student.handler.HealthResponse;
import mx.com.izzi.student.handler.StatusResponse;
import mx.com.izzi.student.jms.JmsProcessor;
import mx.com.izzi.student.xml.Jaxb;
import mx.com.izzi.student.xml.mapper.CreateProductImpMapper;
import mx.com.izzi.student.xml.xsd.java.obj.Item;
import mx.com.izzi.student.xml.xsd.java.obj.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Slf4j
public class AppController implements App{

    @Autowired
    ProductServiceImp service;

    @Autowired
    Jaxb jaxb_xml;

    @Autowired
    JmsProcessor jms;

    @Autowired
    FilenameGenerator generator;

    @Override
    @PostMapping("/sentJMS")
    public ProductResponse sentJMS_File(ProductRequest productRequest) throws JAXBException, ClassNotFoundException, NamingException, JMSException {
        log.info("start sentJMS_File: {}", productRequest.toString());

        /* reading the db */
        log.info("Reading DB...");
        List<ProductDb> messages;
        try {
            messages = service.lookupProduct();
            log.info("product: {}", messages.toString());
        }
        catch (Exception e) {
            return ProductResponse.builder()
                    .withStatus(StatusResponse.builder().withMessage("Database Error: " + e).withSuccess(false)
                            .build())
                    .withTotal(0).build();
        }
        /* startup converter */
        Marshaller jaxbMarshaller = jaxb_xml.getJaxbMarshaller("mx.com.izzi.student.xml.xsd.java.obj.Item");
        log.info("marshaller... ready!");

        List<String> logs = new ArrayList<>();
        logs.add("START JMS ");

        StatusResponse jmsInit = jms.init();
        if (!jmsInit.getSuccess()) {
            return ProductResponse.builder().withStatus(jmsInit).withTotal(0).build();
        }
        logs.add(jmsInit.getMessage());

        /* convert to xml objects */
        Item item = null;
        try {
            item = new CreateProductImpMapper(messages).getMaps();
        }
        catch (Exception e) {
            logs.add("MAPPER: " + item.getIdCorrelation() +": " + e.getLocalizedMessage());
            item = new Item();
        }
        StringWriter sw = new StringWriter();
        if (!item.getProductList().getProduct().isEmpty()) {
            jaxbMarshaller.marshal(item, sw);
            log.info("sending... {}", sw);
        }
        if (sw.toString() != null) {
            StatusResponse jmsSend = jms.send(sw.toString());
            if (!jmsSend.getSuccess()) {
                logs.add("KO- SEND JMS-> Coraletion: " + item.getIdCorrelation() + ", Msg: " + jmsSend.getMessage());
            }
            else {
                logs.add("OK - SEND JMS-> Coraletion: " + item.getIdCorrelation() + ", Msg: " + jmsSend.getMessage());
            }
        }
        StatusResponse jmsClose = jms.close();
        if (!jmsClose.getSuccess()) {
            logs.add("CLOSE Fault JMS: " + jmsClose.getMessage());
        }
        /* logic lookup of records */
        ProductDb productDb = service.findBy_Sku(messages.get(0).getSKU());
        log.info("logic lookup of records: {}", productDb.getName());
        logs.add("logic lookup of records: "+ productDb.getName());

        /* logic update of records */
        List<Long> listMessageId = new ArrayList<>();
        listMessageId= item.getProductList().getProduct().stream().map(Product::getId).collect(Collectors.toList());
        int recordsChanged = service.updateProductType(listMessageId, item.getIdCorrelation());
        log.info("records processed...{}", listMessageId);

        log.info("end: sentJMS_File. ");
        logs.add("CLOSE JMS. ");
        return ProductResponse.builder()
                .withStatus(StatusResponse.builder()
                        .withMessage("success")
                        .withSuccess(true)
                        .build())
                .withTotal(recordsChanged)
                .withLog(logs)
                .build();
    }
    @Override
    @GetMapping("/csv")
    public ResponseEntity<byte[]> getFileCsv() {
        /* reading the db */
        log.info("Reading DB...");
        List<ProductDb> messages;
        messages = service.lookupProduct();
        log.info("product: {}", messages.toString());


        /* converting transaction to reconcile format */
        log.info("Starting convertion to reconcile... ");
        String reconcileData = new MapToReconcile(messages).get_String();
        log.info("Conversion complete...");

        /* create new filename */
        String remoteFilename = generator.get();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", remoteFilename);

        return new ResponseEntity<>(reconcileData.getBytes(), headers, HttpStatus.OK);
    }

    @Value("${izzi.publish.version}")
    private String version;

    @Override
    @GetMapping("/health")
    public HealthResponse getHealth() {
        return HealthResponse.builder().dateTime(LocalDateTime.now()).health("true").version(version).build();
    }
}
