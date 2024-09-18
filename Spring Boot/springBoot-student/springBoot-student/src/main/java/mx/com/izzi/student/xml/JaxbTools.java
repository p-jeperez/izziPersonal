package mx.com.izzi.student.xml;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@Component
public class JaxbTools {

    public Marshaller getJaxbMarshaller(String classNameToMarshall) throws ClassNotFoundException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Class.forName(classNameToMarshall));
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
        return jaxbMarshaller;
    }
}
