package mx.com.izzi.student.handler;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class HealthResponse {
    private LocalDateTime dateTime;
    private String health;
    private String version;
}
