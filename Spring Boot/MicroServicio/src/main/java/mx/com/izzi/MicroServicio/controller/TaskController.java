package mx.com.izzi.MicroServicio.controller;

import lombok.AllArgsConstructor;
import mx.com.izzi.MicroServicio.dto.TaskDto;
import mx.com.izzi.MicroServicio.service.TaskServiceImp;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping(value = ("/izzi/api"))
public class TaskController implements TaskApi {
    private final TaskServiceImp taskService;

    @Override
    @GetMapping("/get/{id}")
    public TaskDto get(@PathVariable Long id) { //localhost:8080/izzi/api/get/4
        return taskService.getTask(id);
    }

    @Override
    @GetMapping("/getAll")
    public List<TaskDto> getAll() {
        return taskService.getTaskAll();
    }

    @Override
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable  String id) {
            return taskService.deleteTask(id);
    }
}
