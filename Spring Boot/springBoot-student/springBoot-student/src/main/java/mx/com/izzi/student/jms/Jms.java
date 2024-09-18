package mx.com.izzi.student.jms;

import mx.com.izzi.student.handler.StatusResponse;
import javax.jms.JMSException;
import javax.naming.NamingException;


public interface Jms {

    public StatusResponse init() throws NamingException, JMSException;
    public StatusResponse post(String msg) throws JMSException;
    public StatusResponse close() throws JMSException;
}
