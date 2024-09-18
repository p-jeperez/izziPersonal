package mx.com.izzi.MicroServicio.service;


import lombok.extern.slf4j.Slf4j;
import mx.com.izzi.MicroServicio.dto.TaskDto;
import mx.com.izzi.MicroServicio.entity.Task;
import mx.com.izzi.MicroServicio.mapper.TaskMapper;
import mx.com.izzi.MicroServicio.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
        taskDto = addTaskDto(task, taskDto);
        log.info(" add task success -> Id: {}. Title: {} [JP- > success: addTask]",taskDto.getId(), taskDto.getTitle());
        return taskDto;
    }
    private TaskDto addTaskDto(TaskDto task, TaskDto taskDto){
        Task saved = repository.save(mapper.toTask(task));
        taskDto = mapper.toDto(saved);
        return taskDto;
    }

    @Override
    public TaskDto upTask(TaskDto task) {
        TaskDto taskDto = null;
        return taskDto;

    }

    @Override
    public TaskDto getTask(Long id) {
        TaskDto taskDto = null;
        try {
            Task taskObj = repository.getReferenceById(id);
            log.info("{}: get Object task, Id: {}, Description: {} . [JP -> success: getReferenceById]", tag, taskObj.getTitle(), taskObj.getDescription());
            taskDto = mapper.toDto(taskObj);
        } catch (Exception e) {
            taskDto = new TaskDto();
        }
        return taskDto;
    }

    @Override
    public List<TaskDto> getTaskAll() {
        TaskDto taskDto = null;
        List<Task> taskObjList = null;
        List<TaskDto> listTaskDto =  new ArrayList<TaskDto>();
        taskObjList = repository.findAll();
        for (Task taskObj : taskObjList) {
            taskDto = mapper.toDto(taskObj);
            listTaskDto.add(taskDto);
            log.info("{}: getAll List Task, Id: {}, Description: {} . [JP -> success: findAll]", tag, taskObj.getTitle(), taskObj.getDescription());
        }
        return listTaskDto;
    }

    @Override
    public String deleteTask(String id) {
        String response = null;
        try {
            log.info("{}: delete Object task, Id: {}. [JP -> Iniciar: deleteTask]",tag,id);
            if (repository.existsById(Long.parseLong(id))){
                repository.deleteById(Long.parseLong(id));
                response = "OK, ";
                log.info("{}: OK delete Object task, Id: {}. [JP -> success: deleteTask]",tag,id);
            }else{
                response = "KO, No existe registro para borrar";
                log.info("{}: KO delete Object task, Id: {}. [JP -> Not found id: deleteTask]",tag,id);
            }
            return response;
        } catch (Exception e) {
            log.info("{}: KO delete Object task, Id: {}. [JP -> Exception: deleteTask], {} ",tag,id,e.getMessage());
            return "KO, " + e.getMessage();
        }
    }
}
