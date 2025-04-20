package kz.bbrent.api.diplom.service.auth;

import io.vavr.control.Try;
import kz.bbrent.api.diplom.domain.dto.auth.RegistrationDto;
import kz.bbrent.api.diplom.domain.dto.auth.RegistrationResponse;
import kz.bbrent.api.diplom.domain.dto.auth.VerifyOtpRequest;
import kz.bbrent.api.diplom.domain.dto.mail.RequestToSendEmailDto;
import kz.bbrent.api.diplom.domain.entity.auth.RegisterUser;
import kz.bbrent.api.diplom.domain.entity.auth.Users;
import kz.bbrent.api.diplom.domain.enums.UserRole;
import kz.bbrent.api.diplom.domain.exceptions.ApiException;
import kz.bbrent.api.diplom.domain.repository.auth.RegisterUserRepository;
import kz.bbrent.api.diplom.domain.repository.auth.UserRepository;
import kz.bbrent.api.diplom.service.external.email.MailSenderService;
import kz.bbrent.api.diplom.service.external.otp.OtpService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private static final Logger log = LoggerFactory.getLogger(RegistrationService.class);
    private final PasswordEncoder passwordEncoder;
    private final RegisterUserRepository registerUserRepository;
    private final UserRepository userRepository;
    private final OtpService otpService;
    private final MailSenderService mailSenderService;

    public RegistrationResponse register(RegistrationDto registrationDto) {
        if (userRepository.existsByUsername(registrationDto.username())) {
            throw new ApiException("Уже есть пользователь с таким именем");
        }

        String otpCode = otpService.generateOtp();
        String message = "Ваш код подтверждения: " + otpCode;
        var requestToSendEmailDto = new RequestToSendEmailDto(
                registrationDto.username(),
                null,
                "PC_CLUB",
                "Ваш код подтверждения",
                message
        );
        sendOtpEmailAsync(requestToSendEmailDto);

        var registerUser = new RegisterUser();

        var registerUser1 = registerUserRepository.findByUsername(registrationDto.username())
                .map(user -> {
                    user.setRegistrationTryCount(user.getRegistrationTryCount() + 1);
                    user.setOtpCode(otpCode);
                    return user;
                })
                .orElseGet(() -> {
                    registerUser.setUsername(registrationDto.username());
                    registerUser.setPassword(passwordEncoder.encode(registrationDto.password()));
                    registerUser.setName(registrationDto.name());
                    registerUser.setOtpCode(otpCode);
                    registerUser.setLastName(registrationDto.lastname());
                    return registerUser;
                });
        log.info("user password: {}", registerUser1.getPassword());
        registerUserRepository.save(registerUser1);
        return new RegistrationResponse(
                "OTP-код успешно отправлен.",
                "OTP expires in " + registerUser1.getOtpExpiryDate().toString()
        );
    }

    public RegistrationResponse verifyOtpCode(VerifyOtpRequest verifyOtpRequest) {
        return Try.of(() -> registerUserRepository.findByUsername(verifyOtpRequest.username()))
                .getOrElseThrow(ex -> new ApiException("Error in database with message: " + ex.getMessage()))
                .map(registerUser -> {
                    String code = registerUser.getOtpCode();
                    if (code.equals(verifyOtpRequest.otp())) {
                        updateUser(registerUser);
                        return new RegistrationResponse("OTP-код успешно подтвержден.", "User registered successfully.");
                    } else {
                        throw new ApiException("OTP-код неверный или истек.");
                    }
                })
                .orElseThrow();
    }

    @Async
    public CompletableFuture<Void> sendOtpEmailAsync(RequestToSendEmailDto requestToSendEmailDto) {
        return CompletableFuture.runAsync(() -> {
            String result = mailSenderService.sendSimpleMessage(requestToSendEmailDto);
            log.info("Result of sending email: {} ", result);
            if (!"OK".equals(result)) {
                throw new ApiException("Проблема отправки OTP-кода попробуйте еще раз");
            }
        });
    }

    @Transactional
    protected void updateUser(RegisterUser registerUser) {
        Users users = new Users();
        users.setUsername(registerUser.getUsername());
        users.setPassword(registerUser.getPassword());
        users.setName(registerUser.getName());
        users.setEnabled(true);
        users.setLastname(registerUser.getLastName());
        users.setRole(UserRole.USER.getRole());
        userRepository.save(users);
        registerUserRepository.delete(registerUser);
    }
}
