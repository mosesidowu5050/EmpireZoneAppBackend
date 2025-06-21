package org.mosesidowu.empirezone.services;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.empirezone.data.models.User;
import org.mosesidowu.empirezone.data.repository.UserRepository;
import org.mosesidowu.empirezone.dtos.requests.LoginUserRequestDTO;
import org.mosesidowu.empirezone.dtos.requests.RegisterUserRequestDTO;
import org.mosesidowu.empirezone.dtos.requests.UpdateUserRequestDTO;
import org.mosesidowu.empirezone.dtos.responses.LoginUserResponseDTO;
import org.mosesidowu.empirezone.dtos.responses.RegisterUserResponseDTO;
import org.mosesidowu.empirezone.exception.EmailDoesntExistException;
import org.mosesidowu.empirezone.exception.EmailExistException;
import org.mosesidowu.empirezone.exception.PhoneNumberExistException;
import org.mosesidowu.empirezone.exception.UserException;
import org.mosesidowu.empirezone.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.mosesidowu.empirezone.utils.Mapper.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public RegisterUserResponseDTO register(RegisterUserRequestDTO userRegisterRequest) {
        isEmailAndPhoneNumberExist(userRegisterRequest.getEmail(), userRegisterRequest.getPhoneNumber());
        User user = getUser(userRegisterRequest);
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        User savedUser = userRepository.save(user);

        return getRegisterUserResponseDTO(savedUser);
    }


    @Override
    public LoginUserResponseDTO login(LoginUserRequestDTO userLoginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword())
            );

            User user = userRepository.findUserByEmail(userLoginRequest.getEmail());
            if (user == null) throw  new EmailDoesntExistException("Invalid email or password");

            String token = jwtUtil.generateToken(user.getEmail());
            LoginUserResponseDTO loginUserResponseDTO = new LoginUserResponseDTO();
            loginUserResponseDTO.setUserId(user.getId());
            loginUserResponseDTO.setToken(token);
            loginUserResponseDTO.setMessage("Login Successful");

            return loginUserResponseDTO;
        }
        catch (BadCredentialsException ex) {
            throw new UserException("Invalid email or password");
        }

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
