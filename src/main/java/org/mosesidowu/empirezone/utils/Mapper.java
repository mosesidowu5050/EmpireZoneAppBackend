package org.mosesidowu.empirezone.utils;

import jdk.jshell.spi.ExecutionControl;
import org.mosesidowu.empirezone.data.models.User;
import org.mosesidowu.empirezone.dtos.requests.RegisterUserRequestDTO;
import org.mosesidowu.empirezone.dtos.responses.RegisterUserResponseDTO;
import org.mosesidowu.empirezone.exception.UserException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Mapper {

    public static User getUser(RegisterUserRequestDTO userRegisterRequest) {
        validateRegisterUserRequest(userRegisterRequest);
        String validateUserFullName = validateUserFullName(userRegisterRequest.getFullName());

        User user = new User();
        user.setFullName(validateUserFullName);
        user.setEmail(userRegisterRequest.getEmail());
//        user.setPassword(userRegisterRequest.getPassword());
        user.setPhoneNumber(userRegisterRequest.getPhoneNumber());
        user.setRole(userRegisterRequest.getRole());
        user.setLocation(userRegisterRequest.getLocation());

        return user;
    }

    public static RegisterUserResponseDTO getRegisterUserResponseDTO(User savedUser) {
        validateUserId(savedUser.getId());
        validateUserCreatedAt(savedUser.getCreatedAt());

        RegisterUserResponseDTO registerUserResponseDTO = new RegisterUserResponseDTO();
        registerUserResponseDTO.setUserId(savedUser.getId());
        registerUserResponseDTO.setFullName(capitalizeName(savedUser.getFullName()));
        registerUserResponseDTO.setEmail(savedUser.getEmail());
        registerUserResponseDTO.setPhoneNumber(savedUser.getPhoneNumber());
        registerUserResponseDTO.setLocation(savedUser.getLocation());
        registerUserResponseDTO.setCreatedAt(savedUser.getCreatedAt() );
        registerUserResponseDTO.setRole(savedUser.getRole().name().toLowerCase());
        registerUserResponseDTO.setMessage("Registered Successfully");

        return registerUserResponseDTO;
    }

    private static String capitalizeName(String name) {
        return Arrays.stream(name.split(" "))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(" "));
    }


    private static void validateRegisterUserRequest(RegisterUserRequestDTO registerUserRequestDTO){
        validateEmailPattern(registerUserRequestDTO.getEmail());
        validateUserPhoneNumber(registerUserRequestDTO.getPhoneNumber());
        validateUserLocation(registerUserRequestDTO.getLocation());
        validateUserRole(registerUserRequestDTO.getRole().name().toLowerCase());
    }

    private static void validateUserId(String userId) {
        boolean isNotValidId = userId == null;
        if (!isNotValidId) throw new UserException("Invalid User Id");
    }

    private static String validateUserFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) throw new UserException("Full Name cannot be null or empty");
        if (!fullName.matches("^[a-zA-Z\\s]+$")) throw new UserException("Full Name must contain only letters and spaces");

        return fullName.trim().toLowerCase();
    }

    private static void validateEmailPattern(String email) {
        boolean isNotValidEmail = email == null || email.isEmpty();
        if (isNotValidEmail) throw new UserException("Email is null or empty");

        boolean isValidEmail = email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        if (!isValidEmail) throw new UserException("Invalid email format.");
    }

    private static void validateUserPhoneNumber(String phoneNumber) {
        boolean isNotValidPhoneNumber = phoneNumber == null || phoneNumber.isEmpty();
        if (isNotValidPhoneNumber) throw new UserException("Phone Number is null or empty");
    }

    private static void validateUserLocation(String location) {
        boolean isNotValidLocation = location == null || location.isEmpty();
        if (isNotValidLocation) throw new UserException("Location is null or empty");
    }

    private static void validateUserCreatedAt(LocalDateTime createdAt) {
        boolean isNotValidCreatedAt = createdAt == null;
        if (isNotValidCreatedAt) throw new UserException("Date and time is empty");
    }

    private static void validateUserRole(String role) {
        boolean isNotValidRole = role == null || role.isEmpty();
        if (isNotValidRole) throw new UserException("Role is null or empty");
    }

}
