package kz.bbrent.api.diplom.controller;

import jakarta.validation.Valid;
import kz.bbrent.api.diplom.domain.dto.auth.*;
import kz.bbrent.api.diplom.domain.dto.auth.ChangePasswordRequest;
import kz.bbrent.api.diplom.service.auth.ChangePassword;
import kz.bbrent.api.diplom.service.auth.RegistrationService;
import kz.bbrent.api.diplom.service.auth.UsernamePasswordAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static kz.bbrent.api.diplom.utills.codes.Uris.API_V1;
import static kz.bbrent.api.diplom.utills.codes.Uris.AUTH;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1 + AUTH)
public class AuthController {

    private final RegistrationService registrationService;
    private final UsernamePasswordAuthenticationManager authenticationService;
    private final ChangePassword changePassword;


    @PostMapping("/login")
    public ResponseEntity<Tokens> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.signIn(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationDto registrationDto) {
        return ResponseEntity.ok(registrationService.register(registrationDto));
    }

    @PostMapping("/verify")
    public ResponseEntity<RegistrationResponse> verifyRegistration(@Valid @RequestBody VerifyOtpRequest verifyOtpRequest) {
        return ResponseEntity.ok(registrationService.verifyOtpCode(verifyOtpRequest));
    }

    @PostMapping("/profile")
    public ResponseEntity<Object> userInfo() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> update(@RequestBody ChangePasswordRequest request) {
        changePassword.changePassword(request);
        return ResponseEntity.ok().build();
    }
}
