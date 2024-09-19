package mx.com.izzi.student.xml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaxbConfig {
    @Bean(name = "Bean_Jaxb")
    public Jaxb getJaxb() {
        return new JaxbImp();
    }
}
