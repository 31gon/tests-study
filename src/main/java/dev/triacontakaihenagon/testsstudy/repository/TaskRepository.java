package dev.triacontakaihenagon.testsstudy.repository;

import dev.triacontakaihenagon.testsstudy.entity.Task;
import dev.triacontakaihenagon.testsstudy.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    List<Task> findByStatus(TaskStatus status);
}