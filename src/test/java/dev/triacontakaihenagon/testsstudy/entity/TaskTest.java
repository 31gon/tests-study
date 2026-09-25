package dev.triacontakaihenagon.testsstudy.entity;

import dev.triacontakaihenagon.testsstudy.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class TaskTest {

    @Autowired
    TaskRepository taskRepository;

    @ParameterizedTest
    @MethodSource("overdueCases")
    void isOverdue_returnsExpected(LocalDateTime dueDate, boolean expected) {
        Task task = new Task();
        task.setDueDate(dueDate);

        assertThat(task.isOverdue()).isEqualTo(expected);
    }

    static Stream<Arguments> overdueCases() {
        return Stream.of(
                Arguments.of(LocalDateTime.now().minusDays(1), true),
                Arguments.of(LocalDateTime.now().plusDays(1), false),
                Arguments.of(null, false)
        );
    }
}
