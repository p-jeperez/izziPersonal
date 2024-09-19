package mx.com.izzi.student.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfig {
    @Bean(name = "Bean_Jms")
    public Jms getConfigJMS() {
        return new JmsImpl();
    }
}

