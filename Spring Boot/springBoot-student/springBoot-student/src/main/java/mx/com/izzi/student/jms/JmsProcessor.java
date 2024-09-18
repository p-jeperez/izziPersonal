package mx.com.izzi.student.jms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.izzi.student.handler.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.naming.NamingException;

@Slf4j
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class JmsProcessor {
    @Autowired
    Jms jms;

    public StatusResponse init() throws NamingException, JMSException
    {
        return jms.init();
    }

    public StatusResponse send(String message) throws JMSException {
        log.info("Starting send...");
        return jms.post(message);
    }

    public StatusResponse close() throws JMSException
    {
        return jms.close();
    }
}
