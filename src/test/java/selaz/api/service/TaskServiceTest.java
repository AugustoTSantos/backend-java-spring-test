package selaz.api.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import selaz.api.entity.Status;
import selaz.api.entity.Task;
import selaz.api.repository.TaskRepository;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTasks() {
        Task task1 = new Task();
        Task task2 = new Task();
        List<Task> tasks = Arrays.asList(task1, task2);
        
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setTitle("New Task");
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);

        assertNotNull(result);
        assertEquals("New Task", result.getTitle());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Updated Task");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        Task taskDetails = new Task();
        taskDetails.setTitle("Updated Task");
        Task result = taskService.updateTask(1L, taskDetails);

        assertNotNull(result);
        assertEquals("Updated Task", result.getTitle());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testDeleteTask() {
        when(taskRepository.existsById(1L)).thenReturn(true);

        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllByStatus() {
        Task task1 = new Task();
        task1.setStatus(Status.PENDENTE);
        Task task2 = new Task();
        task2.setStatus(Status.PENDENTE);
        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskRepository.findByStatus(Status.PENDENTE)).thenReturn(tasks);

        List<Task> result = taskService.getAllByStatus(Status.PENDENTE);

        assertEquals(2, result.size());
        verify(taskRepository, times(1)).findByStatus(Status.PENDENTE);
    }

    @Test
    public void testSortByDueDate() {
        Task task1 = new Task();
        task1.setDueDate(new Date(2024, 7, 28));
        Task task2 = new Task();
        task2.setDueDate(new Date(2024, 7, 29));
        List<Task> tasks = Arrays.asList(task2, task1);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.sortByDueDate();

        assertEquals(task1, result.get(0));
        assertEquals(task2, result.get(1));
        verify(taskRepository, times(1)).findAll();
    }
}
