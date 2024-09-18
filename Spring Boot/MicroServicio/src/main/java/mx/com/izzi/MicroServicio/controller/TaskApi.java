package mx.com.izzi.MicroServicio.controller;

import mx.com.izzi.MicroServicio.dto.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface TaskApi {
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TaskDto get(@PathVariable Long id);

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<TaskDto> getAll();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    String delete(@PathVariable String id);

}
