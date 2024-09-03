package mx.com.izzi.MicroServicio.controller;

import mx.com.izzi.MicroServicio.dto.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


public interface GetTaskApi {
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TaskDto get(@PathVariable Long id);

}
