package org.mosesidowu.empirezone.dtos.requests;

import lombok.Data;

@Data
public class LoginUserRequestDTO {

    private String email;
    private String password;
}