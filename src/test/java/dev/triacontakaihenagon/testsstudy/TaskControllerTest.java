package dev.triacontakaihenagon.testsstudy;

import dev.triacontakaihenagon.testsstudy.controller.TaskController;
import dev.triacontakaihenagon.testsstudy.dto.TaskResponse;
import dev.triacontakaihenagon.testsstudy.entity.Task;
import dev.triacontakaihenagon.testsstudy.mapper.TaskMapper;
import dev.triacontakaihenagon.testsstudy.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    TaskService taskService;
    @MockitoBean
    TaskMapper taskMapper;

    @Test
    void getTask_returns200() throws Exception {
        Task task = new Task();
        TaskResponse response = new TaskResponse();

        when(taskService.getTaskById(1L, null, false)).thenReturn(task);
        when(taskMapper.toResponse(task)).thenReturn(response);
        mockMvc.perform(get("/tasks/1")).andExpect(status().isOk());
    }
}
