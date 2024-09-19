package mx.com.izzi.student.xml;


import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public interface Jaxb {
    public Marshaller getJaxbMarshaller(String classNameToMarshall) throws ClassNotFoundException, JAXBException;
}
