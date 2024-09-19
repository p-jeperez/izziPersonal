package mx.com.izzi.student.file;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilenameGeneratorConfig {
    @Bean(name = "Bean_filenameGenerator")
    public FilenameGenerator getReconcileFileGenerator() {
        return new ReconcileFilenameGenerator();
    }
}
