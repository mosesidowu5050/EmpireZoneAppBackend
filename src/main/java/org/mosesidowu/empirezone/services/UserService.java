package org.mosesidowu.empirezone.services;

import org.mosesidowu.empirezone.dtos.requests.LoginUserRequestDTO;
import org.mosesidowu.empirezone.dtos.requests.RegisterUserRequestDTO;
import org.mosesidowu.empirezone.dtos.requests.UpdateUserRequestDTO;
import org.mosesidowu.empirezone.dtos.responses.LoginUserResponseDTO;
import org.mosesidowu.empirezone.dtos.responses.RegisterUserResponseDTO;

import java.util.List;

public interface UserService {

    RegisterUserResponseDTO register(RegisterUserRequestDTO userRegisterRequest);
    LoginUserResponseDTO login (LoginUserRequestDTO userLoginRequest);
    RegisterUserResponseDTO getUserById (String userId);
    List<RegisterUserResponseDTO> getAllUsers();
    void deleteUser(String userId);
    void updateUserProfile(String userId, UpdateUserRequestDTO dto);

}
