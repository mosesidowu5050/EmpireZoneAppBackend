package org.mosesidowu.empirezone.dtos.requests;

import lombok.Data;
import org.mosesidowu.empirezone.data.models.Role;

import java.time.LocalDateTime;

@Data
public class RegisterUserRequestDTO {

    private String fullName;
    private String email;
    private String password;
    private Role role;
    private String phoneNumber;
    private String country;
    private String city;

}
