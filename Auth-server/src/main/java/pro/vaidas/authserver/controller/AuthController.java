package pro.vaidas.authserver.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.vaidas.authserver.model.AuthenticationRequest;
import pro.vaidas.authserver.service.AuthenticationService;
import pro.vaidas.authserver.service.UserFromDbService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;
    private final UserFromDbService userService;

    @PostMapping("/auth")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request) {
        if (userService.doesUserExist(request.getEmail())) {
            HttpHeaders headers = new HttpHeaders();
            String token = service.authenticate(request).getToken();
            headers.set("Authorization", "Bearer " + token);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/validation")
    public String validateToken(@RequestHeader("Authorization") @NonNull String token, HttpServletRequest request) {
        return ("Valid until " + service.tokenIsValidUntil(token));
    }
}
