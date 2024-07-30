package selaz.api.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.util.ReflectionTestUtils;

import selaz.api.entity.Task;
import selaz.api.service.TaskService;

public class TaskControllerTest {

    @Test
    public void test_retrieve_all_tasks_successfully() {
        TaskService taskService = mock(TaskService.class);
        TaskController taskController = new TaskController();
        ReflectionTestUtils.setField(taskController, "taskService", taskService);

        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(taskService.getAllTasks()).thenReturn(tasks);

        List<Task> result = taskController.getAllTasks();

        assertEquals(2, result.size());
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    public void test_retrieve_no_tasks() {
        TaskService taskService = mock(TaskService.class);
        TaskController taskController = new TaskController();
        ReflectionTestUtils.setField(taskController, "taskService", taskService);

        when(taskService.getAllTasks()).thenReturn(Collections.emptyList());

        List<Task> result = taskController.getAllTasks();

        assertTrue(result.isEmpty());
        verify(taskService, times(1)).getAllTasks();
    }

}
