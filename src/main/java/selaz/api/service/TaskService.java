package selaz.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import selaz.api.entity.Status;
import selaz.api.entity.Task;
import selaz.api.entity.User;
import selaz.api.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();

            existingTask.setTitle(taskDetails.getTitle());
            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setCreatedAt(taskDetails.getCreatedAt());
            existingTask.setDueDate(taskDetails.getDueDate());
            existingTask.setStatus(taskDetails.getStatus());
            existingTask.setUser(taskDetails.getUser());

            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Task não encontrada");
        }
    }

    public void deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new RuntimeException("Task não encontrada");
        }
    }

    public List<Task> getAllByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> sortByDueDate() {
        List<Task> tasks = taskRepository.findAll();
        tasks.sort((task1, task2) -> task1.getDueDate().compareTo(task2.getDueDate()));
        return tasks;
    }

    public List<Task> getAllTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }

}
