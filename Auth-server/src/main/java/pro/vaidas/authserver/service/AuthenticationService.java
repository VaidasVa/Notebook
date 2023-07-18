package pro.vaidas.authserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pro.vaidas.authserver.config.JwtValidationService;
import pro.vaidas.authserver.model.AuthenticationRequest;
import pro.vaidas.authserver.model.AuthenticationResponse;
import pro.vaidas.authserver.repository.UserRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final JwtValidationService jwtValidationService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = repository.getUserByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        var jwtToken = jwtValidationService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public Boolean validateTokenifNotExpired(String token){
        if (token.startsWith("Bearer ")){token=token.substring(7);}
        if (token.length()==125) { return jwtValidationService.isTokenExpired(token); }
        else return true;
    }

    public Date tokenIsValidUntil(String token){
        return jwtValidationService.getExpirationFromToken(token);
    }

    public String getUser(String token){
        return jwtValidationService.getUserName(token);
    }
}
