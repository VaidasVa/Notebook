package pro.vaidas.authserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.vaidas.authserver.config.JwtValidationService;
import pro.vaidas.authserver.model.AuthenticationRequest;
import pro.vaidas.authserver.model.AuthenticationResponse;
import pro.vaidas.authserver.controller.RegisterRequest;
import pro.vaidas.authserver.model.Role;
import pro.vaidas.authserver.model.User;
import pro.vaidas.authserver.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;
    private final JwtValidationService jwtValidationService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .notExpired(true)
                .notLocked(true)
                .credentialsNotExpired(true)
                .enabled(true)
                .build();
//        repository.save(user);

        // we also need to return a token
        var jwtToken = jwtValidationService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        // now user is authenticated (email and password are correct),
        // otherwise an error would be thrown
        var user = repository.getUserByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("notfound"));

        var jwtToken = jwtValidationService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
