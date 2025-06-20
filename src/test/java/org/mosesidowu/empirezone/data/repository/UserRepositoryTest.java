package org.mosesidowu.empirezone.data.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mosesidowu.empirezone.data.models.User;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mosesidowu.empirezone.data.models.Role.BUYER;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserRepositoryMockTest {

    @Mock
    private UserRepository userRepository;



    @Test
    void saveOneUser_whenSaveCalled_userIsSaved() {
        User user = getRegisterUser();
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.count()).thenReturn(1L);
        User savedUser = userRepository.save(user);
        long count = userRepository.count();

        assertEquals("Williams Williams", savedUser.getFullName());
        assertEquals(1, count);
        verify(userRepository, times(1)).save(any(User.class));
        verify(userRepository, times(1)).count();
    }

    @Test
    @DisplayName("find user by email")
    void saveUser_whenUserIsSaved_findUserByEmail() {
        User user = getRegisterUser();
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.count()).thenReturn(1L);
        when(userRepository.findUsersByEmail(user.getEmail())).thenReturn(Optional.of(user));

        User savedUser = userRepository.save(user);
        long count = userRepository.count();
        Optional<User> searchEmail = userRepository.findUsersByEmail(savedUser.getEmail());

        assertTrue(searchEmail.isPresent());
        assertEquals(user.getEmail(), searchEmail.get().getEmail());
        assertEquals(1, count);

        verify(userRepository, times(1)).save(any(User.class));
        verify(userRepository, times(1)).findUsersByEmail(user.getEmail());
    }


    @Test
    @DisplayName("find user by email throws exception when email is wrong")
    void saveUser_whenUserIsSaved_throwException_whenEmailIsWrong() {
        when(userRepository.findUsersByEmail("williams10@gmail")).thenReturn(Optional.empty());

        Optional<User> searchEmail = userRepository.findUsersByEmail("williams10@gmail");

        assertTrue(searchEmail.isEmpty());
        verify(userRepository, times(1)).findUsersByEmail("williams10@gmail");
    }


    private User getRegisterUser() {
        User user = new User();
        user.setFullName("Williams Williams");
        user.setEmail("williams1010@gmail.com");
        user.setPassword("williams1010");
        user.setPhoneNumber("09012345678");
        user.setLocation("San Francisco");
        user.setRole(BUYER);
        user.setCreatedAt(LocalDateTime.now());

        return user;
    }

}
