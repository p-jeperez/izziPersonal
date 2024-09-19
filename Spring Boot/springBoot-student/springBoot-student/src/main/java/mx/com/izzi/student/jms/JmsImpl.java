package mx.com.izzi.student.jms;

import lombok.extern.slf4j.Slf4j;
import mx.com.izzi.student.handler.StatusResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@Slf4j
public class JmsImpl implements Jms{

    @Value("${izzi.jms.server.url}")
    private String SERVER_URL;

    @Value("${izzi.jms.jndi.connection_factory}")
    private String CONNECTION_FACTORY;

    @Value("${izzi.jms.jndi.queue}")
    private String QUEUE;

    @Value("${izzi.jndi.initial_context_factory}")
    private String INITIAL_CONTEXT_FACTORY;

    private QueueConnectionFactory queueConnectionFactory;
    private QueueSession queueSession;
    private QueueConnection queueConnection;
    private QueueSender queueSender;
    private Queue queue;
    private TextMessage message;

    public StatusResponse init() throws NamingException, JMSException {
        try
        {
            //initialize context
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
            env.put(Context.PROVIDER_URL, SERVER_URL);
            InitialContext context = new InitialContext(env);
            log.info("initial context... ready!");

            //initialize connection
            queueConnectionFactory = (QueueConnectionFactory) context.lookup(CONNECTION_FACTORY);
            log.info("queue connection factory... ready!");

            queue = (Queue) context.lookup(QUEUE);
            log.info("queue... ready!");

            queueConnection = queueConnectionFactory.createQueueConnection();
            log.info("queue connection... ready!");

            queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            log.info("queue connection session... ready!");

            queueSender = queueSession.createSender(queue);
            log.info("queue sender... ready!");

            message = queueSession.createTextMessage();
            queueConnection.start();
            log.info("queue connection start... ready!");

            return StatusResponse.builder()
                    .withSuccess(true)
                    .withMessage(" Queue connection start... ready!")
                    .build();
        }
        catch(NamingException e)
        {
            return StatusResponse.builder().withSuccess(false).withMessage("JMS Init Error: " + e).build();
        }
    }

    public StatusResponse post(String msg) throws JMSException {
        try
        {
            message.setText(msg);
            queueSender.send(message);
            return StatusResponse.builder()
                    .withSuccess(true)
                    .withMessage("Send message... ready!")
                    .build();
        }
        catch (JMSException e)
        {
            return StatusResponse.builder().withSuccess(false).withMessage("JMS Post Error: " + e).build();
        }
    }

    public StatusResponse close() throws JMSException {
        try
        {
            queueSender.close();
            queueSession.close();
            queueConnection.close();
            return StatusResponse.builder()
                    .withSuccess(true)
                    .withMessage(" Queue connection end... ready!")
                    .build();
        }
        catch(JMSException e)
        {
            return StatusResponse.builder().withSuccess(false).withMessage("JMS Close Error: " + e).build();
        }
    }
}
