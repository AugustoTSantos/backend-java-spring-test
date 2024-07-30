package selaz.api.entity;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void test_task_persistence() {
        Task task = new Task();
        task.setTitle("Sample Task");
        task.setDescription("This is a sample task");
        task.setCreatedAt(new Date());
        task.setDueDate(new Date());
    
        Status status = new Status();
        status.setDescription("In Progress");
        task.setStatus(status);
    
        User user = new User();
        user.setUsername("testuser");
        user.setNivel("admin");
        task.setUser(user);
    
        // trocar por mock
        /*
        entityManager.persist(task);
        entityManager.flush();
    
        assertNotNull(task.getId());
        */
    }

    @Test
    public void test_task_null_title_not_persisted() {
        Task task = new Task();
        task.setDescription("This task has no title");
        task.setCreatedAt(new Date());
        task.setDueDate(new Date());
    
        Status status = new Status();
        status.setDescription("In Progress");
        task.setStatus(status);
    
        User user = new User();
        user.setUsername("testuser");
        user.setNivel("admin");
        task.setUser(user);
    
        // trocar por mock
        /*
        assertThrows(PersistenceException.class, () -> {
            entityManager.persist(task);
            entityManager.flush();
        });
        */
    }

}
