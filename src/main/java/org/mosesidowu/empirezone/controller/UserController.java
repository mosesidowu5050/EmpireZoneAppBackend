package org.mosesidowu.empirezone.controller;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.empirezone.dtos.requests.LoginUserRequestDTO;
import org.mosesidowu.empirezone.dtos.requests.RegisterUserRequestDTO;
import org.mosesidowu.empirezone.dtos.responses.LoginUserResponseDTO;
import org.mosesidowu.empirezone.dtos.responses.RegisterUserResponseDTO;
import org.mosesidowu.empirezone.exception.EmpireZoneAppException;
import org.mosesidowu.empirezone.services.EmailService;
import org.mosesidowu.empirezone.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        try {
            RegisterUserResponseDTO registerUserResponseDTO = userService.register(registerUserRequestDTO);
            return new ResponseEntity<>(registerUserResponseDTO, HttpStatus.OK);
        }
        catch (EmpireZoneAppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserRequestDTO loginUserRequestDTO) {
        try {
            LoginUserResponseDTO loginUserResponseDTO = userService.login(loginUserRequestDTO);
            return new ResponseEntity<>(loginUserResponseDTO, HttpStatus.OK);
        }
        catch (EmpireZoneAppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/send-test")
    public ResponseEntity<?> sendTestEmail(@RequestParam String to) {
        emailService.sendEmail(to, "EmpireZone Test Email", "This is a test email.");
        return ResponseEntity.ok("Email sent to " + to);
    }


}
