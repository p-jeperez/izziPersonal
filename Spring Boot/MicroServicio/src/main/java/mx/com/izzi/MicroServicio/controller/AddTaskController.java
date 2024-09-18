package mx.com.izzi.MicroServicio.controller;

import lombok.AllArgsConstructor;
import mx.com.izzi.MicroServicio.dto.TaskDto;
import mx.com.izzi.MicroServicio.service.TaskServiceImp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping(value = ("/api/izzi-add"))
public class AddTaskController implements AddTaskApi {
    private final TaskServiceImp addTaskService;

    @Override
    @PostMapping("/save")
    public TaskDto save(TaskDto request) {
        return addTaskService.addTask(request);
    }


}
