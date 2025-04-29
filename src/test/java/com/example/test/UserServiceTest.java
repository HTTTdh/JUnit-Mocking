package com.example.test;

import com.example.test.models.User;
import com.example.test.repositories.UserRepository;
import com.example.test.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserName_success() {
        User mockUser = new User(1, "Alice");  // tạo đối tượng giả
        when(userRepository.findById(1)).thenReturn(mockUser);

        String result = userService.getUserName(1);
        assertEquals("Alice", result);
        verify(userRepository).findById(1);
    }
    @Test
    public void testGetUserName_UserNotFound() {
        when(userRepository.findById(1)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.getUserName(1);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository).findById(1);
    }

    @Test
    public void testRegisterUser_success() {
        User mockUser = new User(2, "John");
        userService.registerUser(mockUser);
        verify(userRepository).save(mockUser);

    }

    @Test
    public void testRegisterUser_invalidName() {
        User mockUser = new User(3, "");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(mockUser);
        });

        assertEquals("Invalid user name", exception.getMessage());
        verify(userRepository, never()).save(any());
    }


}
