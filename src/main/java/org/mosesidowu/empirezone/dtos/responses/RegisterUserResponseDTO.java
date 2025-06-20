package org.mosesidowu.empirezone.dtos.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterUserResponseDTO {

    private String userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String location;
    private String role;
    private LocalDateTime createdAt;
    private String message;

}
