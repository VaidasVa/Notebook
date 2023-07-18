package pro.vaidas.notebookclient.service;

import jakarta.servlet.http.Cookie;
import org.springframework.http.ResponseEntity;
import pro.vaidas.notebookclient.model.AuthDetails;

import java.net.URISyntaxException;

public interface Authentication {

    ResponseEntity<String> authenticate(AuthDetails authDetails) throws URISyntaxException;

    Cookie createCookie(String cookieName, String cookieValue);

    Boolean setSecurityContext(String token);
}
