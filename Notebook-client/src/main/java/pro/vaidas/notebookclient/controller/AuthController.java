package pro.vaidas.notebookclient.controller;

import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.vaidas.notebookclient.config.JwtValidationService;
import pro.vaidas.notebookclient.model.AuthDetails;
import pro.vaidas.notebookclient.service.Authentication;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final Authentication service;
    private final JwtValidationService jwtValidationService;



    public AuthController(Authentication service, JwtValidationService jwtValidationService) {
        this.service = service;
        this.jwtValidationService = jwtValidationService;
    }

    @PostMapping
    public ResponseEntity<String> auth(
            @Required @Validated @RequestBody AuthDetails authDetails,
            HttpServletResponse response)
            throws URISyntaxException, UnsupportedEncodingException {

        ResponseEntity<String> answer = service.authenticate(authDetails);
        if (answer.getStatusCode().is2xxSuccessful()) {
            String cookieValue = answer.getHeaders().getFirst("Authorization");
            String encodedCookieValue = URLEncoder.encode(cookieValue, "UTF-8");
            response.addCookie(service.createCookie("token", encodedCookieValue));
            return answer;
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/validate")
    public Jws validate(@RequestBody String token){
        return jwtValidationService.validate(token);
    }
}