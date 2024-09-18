package mx.com.izzi.MicroServicio.service;

import mx.com.izzi.MicroServicio.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto addTask(TaskDto task);
    TaskDto upTask(TaskDto task);
    TaskDto getTask(Long id);
    List<TaskDto> getTaskAll();
    String deleteTask(String id);

}
