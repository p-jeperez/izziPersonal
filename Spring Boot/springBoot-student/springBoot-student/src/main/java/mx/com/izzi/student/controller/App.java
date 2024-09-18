package mx.com.izzi.student.controller;

import mx.com.izzi.student.controller.in.ProductRequest;
import mx.com.izzi.student.controller.out.ProductResponse;
import mx.com.izzi.student.handler.HealthResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;


public interface App {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse sentJMS_File(@RequestBody ProductRequest productRequest) throws JAXBException, ClassNotFoundException, NamingException, JMSException;

    @GetMapping
    public HealthResponse getHealth();



}