package org.mosesidowu.empirezone.dtos.responses;

import lombok.Data;

@Data
public class LoginUserResponseDTO {

    private String userId;
    private String message;
    private String token;

}
