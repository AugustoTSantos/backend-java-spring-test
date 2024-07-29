package selaz.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import selaz.api.entity.Status;
import selaz.api.entity.Task;
import selaz.api.entity.User;

public interface TaskRepository extends JpaRepository<Task, Long>{

    List<Task> findByStatus(Status status);
    List<Task> findByUser(User user);

}
