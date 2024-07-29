package selaz.api.controller;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import selaz.api.entity.Task;
import selaz.api.service.TaskService;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTasks() throws Exception {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");
        task1.setDueDate(new Date());
        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");
        task2.setDueDate(new Date());
        
        when(taskService.getAllTasks()).thenReturn(Arrays.asList(task1, task2));

        mockMvc.perform(get("/tasks"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$[0].title").value("Task 1"))
            .andExpect(jsonPath("$[1].title").value("Task 2"));
    }

    @Test
    public void testCreateTask() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("New Task");
        
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/tasks")
                .contentType("application/json")
                .content("{\"title\": \"New Task\", \"description\": \"Task description\"}"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.title").value("New Task"));
    }

    @Test
    public void testUpdateTask() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Updated Task");
        
        when(taskService.updateTask(eq(1L), any(Task.class))).thenReturn(task);

        mockMvc.perform(put("/tasks/1")
                .contentType("application/json")
                .content("{\"title\": \"Updated Task\", \"description\": \"Updated description\"}"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.title").value("Updated Task"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/tasks/1"))
            .andExpect(status().isOk())
            .andDo(print());

        verify(taskService, times(1)).deleteTask(1L);
    }
}
