package org.mosesidowu.empirezone.dtos.requests;

import lombok.Data;

@Data
public class UpdateUserRequestDTO {

    private String fullName;
    private String phoneNumber;
    private String location;
    private String password;

}
