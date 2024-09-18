package mx.com.izzi.student.controller.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductRequest {
    @JsonProperty
    private String operation;
}
