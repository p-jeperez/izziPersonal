package mx.com.izzi.MicroServicio.mapper;

import javax.annotation.processing.Generated;
import mx.com.izzi.MicroServicio.dto.TaskDto;
import mx.com.izzi.MicroServicio.entity.Task;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-02T14:08:10-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDto toDto(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDto.TaskDtoBuilder taskDto = TaskDto.builder();

        taskDto.id( task.getId() );
        taskDto.title( task.getTitle() );
        taskDto.description( task.getDescription() );
        taskDto.status( task.getStatus() );

        return taskDto.build();
    }

    @Override
    public Task toTask(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        Task.TaskBuilder task = Task.builder();

        task.id( taskDto.getId() );
        task.title( taskDto.getTitle() );
        task.description( taskDto.getDescription() );
        task.status( taskDto.getStatus() );

        return task.build();
    }
}
