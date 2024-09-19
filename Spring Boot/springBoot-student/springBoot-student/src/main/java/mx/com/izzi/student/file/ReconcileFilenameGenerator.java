package mx.com.izzi.student.file;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.ZoneId;
@Slf4j
@Data
public class ReconcileFilenameGenerator implements FilenameGenerator{
    @Value("${izzi.file.prefix}")
    private String provider_name;

    @Value("${izzi.file.postfix}")
    private String postsuffix;

    private String separator = "_";

    @Value("${izzi.file.extension}")
    private String fileextension;

    private Long timestamp = 0L;

    public String get() {
        timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        String returnFilename = provider_name + separator + timestamp + separator + postsuffix + "." + fileextension;
        log.info("Creating unique name: " + returnFilename);
        return returnFilename;
    }
}
