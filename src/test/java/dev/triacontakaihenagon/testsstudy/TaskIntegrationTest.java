package dev.triacontakaihenagon.testsstudy;

import dev.triacontakaihenagon.testsstudy.controller.TaskController;
import dev.triacontakaihenagon.testsstudy.dto.TaskRequest;
import dev.triacontakaihenagon.testsstudy.entity.Role;
import dev.triacontakaihenagon.testsstudy.entity.Task;
import dev.triacontakaihenagon.testsstudy.entity.User;
import dev.triacontakaihenagon.testsstudy.mapper.TaskMapper;
import dev.triacontakaihenagon.testsstudy.repository.CategoryRepository;
import dev.triacontakaihenagon.testsstudy.repository.LabelRepository;
import dev.triacontakaihenagon.testsstudy.repository.TaskRepository;
import dev.triacontakaihenagon.testsstudy.repository.UserRepository;
import dev.triacontakaihenagon.testsstudy.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class TaskIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void getTask_returnsRealSavedTask() throws Exception {
        User user = new User();
        user.setUserName("user");
        user.setRole(Role.USER);
        userRepository.save(user);

        Task task = new Task();
        task.setTitle("Integration test task");
        task.setUser(user);
        Long savedId = taskRepository.save(task).getId();

        mockMvc.perform(get("/tasks/{id}", savedId).param("auth", "user"))
                .andExpect(status().isOk());
    }
}