package selaz.api.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import selaz.api.entity.Task;
import selaz.api.repository.TaskRepository;

public class TaskServiceTest {

    @Test
    public void test_retrieve_all_tasks_successfully() {
        TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
        TaskService taskService = new TaskService();
        ReflectionTestUtils.setField(taskService, "taskRepository", taskRepository);

        List<Task> mockTasks = Arrays.asList(new Task(), new Task());
        Mockito.when(taskRepository.findAll()).thenReturn(mockTasks);

        List<Task> tasks = taskService.getAllTasks();

        Assertions.assertEquals(2, tasks.size());
        Mockito.verify(taskRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void test_update_non_existent_task() {
        TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
        TaskService taskService = new TaskService();
        ReflectionTestUtils.setField(taskService, "taskRepository", taskRepository);

        Long nonExistentTaskId = 1L;
        Task taskDetails = new Task();
        Mockito.when(taskRepository.findById(nonExistentTaskId)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            taskService.updateTask(nonExistentTaskId, taskDetails);
        });

        Assertions.assertEquals("Task não encontrada", exception.getMessage());
        Mockito.verify(taskRepository, Mockito.times(1)).findById(nonExistentTaskId);
    }

}
