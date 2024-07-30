package selaz.api.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import selaz.api.entity.User;
import selaz.api.repository.UserRepository;

public class UserServiceTest {

    @Test
    public void test_retrieve_all_users_successfully() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService();
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);

        List<User> mockUsers = Arrays.asList(new User(), new User());
        Mockito.when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> users = userService.getAllUsers();

        Assertions.assertEquals(2, users.size());
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void test_update_user_not_found() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService();
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);

        Long userId = 1L;
        User userDetails = new User();
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.updateUser(userId, userDetails);
        });

        Assertions.assertEquals("Usuário não encontrado", exception.getMessage());
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
    }

}
