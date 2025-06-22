package org.mosesidowu.empirezone.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class UserServiceImpl implements  UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public RegisterUserResponseDTO register(RegisterUserRequestDTO userRegisterRequest) {
        isEmailExist(userRegisterRequest.getEmail());
        isPhoneNumberExist(userRegisterRequest.getPhoneNumber());

        User user = getUser(userRegisterRequest);
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        User savedUser = userRepository.save(user);

        emailService.sendEmail(
                user.getEmail(),
                "Welcome to EmpireZone!🎉 \n" +
                        "We're excited to have you on board.",
                """
                        You can now explore, post, and manage your own ads with ease\s
                        Whether you're buying or selling, you've joined a growing community that values simplicity, trust, and opportunity.\s
                        If you ever need help, we’re just an email away.\s
                        Let the deals begin!\s
                        
                        
                        Cheers,\s
                        The EmpireZone Team."""
        );

        return getRegisterUserResponseDTO(savedUser);
    }


    @Override
    public LoginUserResponseDTO login(LoginUserRequestDTO userLoginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword())
            );

            User user = userRepository.findUserByEmail(userLoginRequest.getEmail());
            Optional.ofNullable(user)
                    .orElseThrow(() -> new EmailDoesntExistException("Invalid email or password"));

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

    private void isEmailExist(String email) {
        boolean emailExist = userRepository.existsByEmail(email);
        if (emailExist) throw new EmailExistException("Email already exist");
    }

    private void isPhoneNumberExist(String phoneNumber) {
        Optional<User> phoneNumberExist = userRepository.findUsersByPhoneNumber(phoneNumber);
        if (phoneNumberExist.isPresent()) throw new PhoneNumberExistException("Phone number already exist");
    }


    @Override
    public RegisterUserResponseDTO getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));
        return getRegisterUserResponseDTO(user);
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
