package mx.com.izzi.MicroServicio.controller;

import lombok.AllArgsConstructor;
import mx.com.izzi.MicroServicio.dto.TaskDto;
import mx.com.izzi.MicroServicio.service.TaskServiceImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping(value = ("/izzi/api"))
public class GetTaskController implements GetTaskApi {
    private final TaskServiceImp getTaskService;

    @Override
    @GetMapping("/get/{id}")
    public TaskDto get(@PathVariable Long id) { //localhost:8080/izzi/api/get/4
        return getTaskService.getTask(id);
    }


}
