package mx.com.izzi.MicroServicio.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.izzi.MicroServicio.dto.TaskDto;
import mx.com.izzi.MicroServicio.entity.Task;
import mx.com.izzi.MicroServicio.mapper.TaskMapper;
import mx.com.izzi.MicroServicio.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class TaskServiceImp  implements TaskService{
    @Autowired
    private  TaskRepository repository;
    @Autowired
    private  TaskMapper mapper;
    @Value("${spring.application.name}")
    private String tag;

    @Override
    public TaskDto addTask(TaskDto task) {
        log.info("{}: add task request: {}, {} . [JP -> iniciar: addTask]",tag,task.getTitle(),task.getDescription());
        TaskDto taskDto = null;
        if (Objects.nonNull(task)){
            taskDto = getTaskDto(task,taskDto);
            log.info(" add task success -> Id: {}. Title: {} [JP- > success: addTask]",taskDto.getId(), taskDto.getTitle());
        }
        return taskDto;
    }
    private TaskDto getTaskDto(TaskDto task, TaskDto taskDto){
        Task saved = repository.save(mapper.toTask(task));
        if(Objects.nonNull(saved)){
            taskDto = mapper.toDto(saved);
        }
        return taskDto;
    }

    @Override
    public TaskDto upTask(TaskDto task) {
        return null;
    }

    @Override
    public TaskDto getTask(Long id) {
        TaskDto taskDto = null;
        Task taskObj = repository.getReferenceById(id);
        if(Objects.nonNull(taskObj)){
            log.info("{}: get Object task, Id: {}, Description: {} . [JP -> success: getReferenceById]",tag,taskObj.getTitle(),taskObj.getDescription());
            taskDto = mapper.toDto(taskObj);
        }
        return taskDto;
    }


    @Override
    public List<TaskDto> getTask() {
        return List.of();
    }

    @Override
    public void deleteTask(String id) {

    }
}
