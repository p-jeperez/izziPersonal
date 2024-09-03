package mx.com.izzi.MicroServicio.mapper;

import mx.com.izzi.MicroServicio.dto.TaskDto;
import mx.com.izzi.MicroServicio.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task toTask(TaskDto taskDto);


}
