package kz.bbrent.api.diplom.service.auth;

import io.vavr.control.Try;
import jakarta.security.auth.message.AuthException;
import kz.bbrent.api.diplom.domain.dto.auth.LoginRequest;
import kz.bbrent.api.diplom.domain.dto.auth.Tokens;
import kz.bbrent.api.diplom.domain.exceptions.ApiException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsernamePasswordAuthenticationManager implements AuthenticationManager {
    private static final Logger log = LogManager.getLogger(UsernamePasswordAuthenticationManager.class);
    private final TokenFactoryService tokenService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UsernamePasswordAuthenticationManager(TokenFactoryService tokenService, UserService userService, PasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public Tokens signIn(LoginRequest request) {
        return Try.of(() -> authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password())))
                .map(tokenService::generateToken)
                .getOrElseThrow(e -> new ApiException("Invalid username or password", e));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            log.error("Пароль не подходит");
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }
}
