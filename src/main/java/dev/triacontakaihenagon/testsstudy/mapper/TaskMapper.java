package dev.triacontakaihenagon.testsstudy.mapper;

import dev.triacontakaihenagon.testsstudy.dto.TaskResponse;
import dev.triacontakaihenagon.testsstudy.entity.Comment;
import dev.triacontakaihenagon.testsstudy.entity.Label;
import dev.triacontakaihenagon.testsstudy.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "comments", target = "commentsId")
    @Mapping(source = "labels", target = "labelsId")
    TaskResponse toResponse(Task task);

    List<TaskResponse> toResponseList(List<Task> tasks);

    default Long commentToId(Comment comment) {
        return comment.getId();
    }

    default Long labelToId(Label label) {
        return label.getId();
    }
}