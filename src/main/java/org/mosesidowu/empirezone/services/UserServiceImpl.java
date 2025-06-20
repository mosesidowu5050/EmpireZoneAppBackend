package org.mosesidowu.empirezone.services;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.empirezone.data.models.User;
import org.mosesidowu.empirezone.data.repository.UserRepository;
import org.mosesidowu.empirezone.dtos.requests.LoginUserRequestDTO;
import org.mosesidowu.empirezone.dtos.requests.RegisterUserRequestDTO;
import org.mosesidowu.empirezone.dtos.requests.UpdateUserRequestDTO;
import org.mosesidowu.empirezone.dtos.responses.LoginUserResponseDTO;
import org.mosesidowu.empirezone.dtos.responses.RegisterUserResponseDTO;
import org.mosesidowu.empirezone.exception.EmailExistException;
import org.mosesidowu.empirezone.exception.PhoneNumberExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.mosesidowu.empirezone.utils.Mapper.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService {

    private final UserRepository userRepository;

    @Override
    public RegisterUserResponseDTO register(RegisterUserRequestDTO userRegisterRequest) {
        isEmailAndPhoneNumberExist(userRegisterRequest.getEmail(), userRegisterRequest.getPhoneNumber());
        User user = getUser(userRegisterRequest);
        User savedUser = userRepository.save(user);

        return getRegisterUserResponseDTO(savedUser);
    }


    @Override
    public LoginUserResponseDTO login(LoginUserRequestDTO userLoginRequest) {
        Optional<User> confirmEmail = userRepository.findUsersByEmail(userLoginRequest.getEmail());
        Optional<User> confirmPassword = userRepository.findUsersByPassword(userLoginRequest.getPassword());

        if (confirmEmail.isPresent() && confirmPassword.isPresent()) {
            LoginUserResponseDTO loginUserResponseDTO = new LoginUserResponseDTO();

        }

        return null;
    }

    private void isEmailAndPhoneNumberExist(String email, String phoneNumber) {
        Optional<User> emailExist = userRepository.existsByEmail(email);
        Optional<User> phoneNumberExist = userRepository.existsByPhoneNumber(phoneNumber);
        if (emailExist.isPresent()) throw new EmailExistException("Email already exist");
        if (phoneNumberExist.isPresent()) throw new PhoneNumberExistException("Phone number already exist");
    }

    @Override
    public RegisterUserResponseDTO getUserById(String userId) {
        return null;
    }

    @Override
    public List<RegisterUserResponseDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public void updateUserProfile(String userId, UpdateUserRequestDTO dto) {

    }
}
