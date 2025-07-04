package org.mosesidowu.empirezone.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("user_db")
public class User {

    @Id
    private String id;
    private String fullName;
    private String email;
    private String password;
    private Role role;
    private String phoneNumber;
    private String country;
    private String city;
    private LocalDateTime createdAt;

}
