package mx.com.izzi.student.controller.out;

import lombok.Data;
import mx.com.izzi.student.handler.StatusResponse;
import java.util.List;

@Data
public class ProductResponse {
    private StatusResponse status;
    private long total;
    private List<String> log;


    private ProductResponse(Builder builder) {
        this.status = builder.status;
        this.total = builder.total;
        this.log = builder.log;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private StatusResponse status;
        private long total;
        private List<String> log;

        private Builder() {
        }

        public Builder withStatus(StatusResponse status) {
            this.status = status;
            return this;
        }

        public Builder withTotal(long total) {
            this.total = total;
            return this;
        }

        public Builder withLog(List<String> log) {
            this.log = log;
            return this;
        }

        public ProductResponse build() {
            return new ProductResponse(this);
        }
    }
}
