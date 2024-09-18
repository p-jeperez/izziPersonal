package mx.com.izzi.student.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.Generated;

@Data
public class StatusResponse {
    private Boolean success;
    private String message;

    private StatusResponse(Builder builder) {
        this.success = builder.success;
        this.message = builder.message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Boolean success;
        private String message;

        private Builder() {
        }

        public Builder withSuccess(Boolean success) {
            this.success = success;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public StatusResponse build() {
            return new StatusResponse(this);
        }
    }

}
