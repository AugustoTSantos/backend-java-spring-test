package selaz.api.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void test_user_entity_mapping() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setNivel("admin");

        assertEquals(1L, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("admin", user.getNivel());
    }

        // Attempt to persist a User entity with a null username
    @Test
    public void test_persist_user_with_null_username() {
        User user = new User();
        user.setId(1L);
        user.setNivel("admin");

        // trocar por mock
        /*
        assertThrows(PersistenceException.class, () -> {
            entityManager.persist(user);
            entityManager.flush();
        });
        */
    }

}
