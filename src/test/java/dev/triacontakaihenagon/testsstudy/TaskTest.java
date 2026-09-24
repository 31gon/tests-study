package dev.triacontakaihenagon.testsstudy;

import dev.triacontakaihenagon.testsstudy.entity.Task;
import dev.triacontakaihenagon.testsstudy.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class TaskTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void isOverdue_returnsTrueWhenDueDateInPast() {
        Task task = new Task();
        task.setDueDate(LocalDateTime.now().minusDays(1));
        taskRepository.save(task);

        assertThat(task.isOverdue()).isTrue();
    }

    @Test
    void isOverdue_returnFalseWhenDueDateIsNull() {
        Task task = new Task();
        taskRepository.save(task);

        assertThat(task.isOverdue()).isFalse();
    }
}
