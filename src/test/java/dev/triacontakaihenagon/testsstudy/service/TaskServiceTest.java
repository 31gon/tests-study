package dev.triacontakaihenagon.testsstudy.service;

import dev.triacontakaihenagon.testsstudy.entity.Task;
import dev.triacontakaihenagon.testsstudy.exception.TaskNotFoundException;
import dev.triacontakaihenagon.testsstudy.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    @Test
    void getTask_throwsWhenNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(1L, "someuser", false));
    }
    @Test
    void getOverdueTasks_returnsOnlyOverdueTasks() {
        Task overdueTask = new Task();
        overdueTask.setDueDate(LocalDateTime.now().minusDays(1));

        Task futureTask = new Task();
        futureTask.setDueDate(LocalDateTime.now().plusDays(1));

        Task noDueDateTask = new Task();

        when(taskRepository.findAll())
                .thenReturn(List.of(overdueTask, futureTask, noDueDateTask));

        List<Task> result = taskService.getOverdueTasks();

        assertThat(result).containsExactly(overdueTask);
    }
}