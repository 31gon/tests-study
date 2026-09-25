package dev.triacontakaihenagon.testsstudy.repository;

import dev.triacontakaihenagon.testsstudy.entity.Task;
import dev.triacontakaihenagon.testsstudy.entity.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void findByStatus_returnsMatchingTasks() {
        Task task = new Task();
        task.setTitle("Test task");
        task.setStatus(TaskStatus.TODO);
        taskRepository.save(task);
        List<Task> result = taskRepository.findByStatus(TaskStatus.TODO);

        assertThat(result).hasSize(1);
    }
}