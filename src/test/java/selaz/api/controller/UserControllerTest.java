package selaz.api.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.util.ReflectionTestUtils;

import selaz.api.entity.User;
import selaz.api.service.UserService;

public class UserControllerTest {

    @Test
    public void test_retrieve_all_users_successfully() {
        UserService userService = mock(UserService.class);
        UserController userController = new UserController();
        ReflectionTestUtils.setField(userController, "userService", userService);

        List<User> users = Arrays.asList(new User(), new User());
        when(userService.getAllUsers()).thenReturn(users);

        List<User> result = userController.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void test_retrieve_no_users() {
        UserService userService = mock(UserService.class);
        UserController userController = new UserController();
        ReflectionTestUtils.setField(userController, "userService", userService);

        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        List<User> result = userController.getAllUsers();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userService, times(1)).getAllUsers();
    }

}
