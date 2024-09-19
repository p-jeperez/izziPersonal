package mx.com.izzi.student.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class JaxbImp implements  Jaxb{
    public Marshaller getJaxbMarshaller(String classNameToMarshall) throws ClassNotFoundException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Class.forName(classNameToMarshall));
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
        return jaxbMarshaller;
    }
}
