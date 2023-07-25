package pro.vaidas.notebookclient.service;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.vaidas.notebookclient.config.JwtValidationService;
import pro.vaidas.notebookclient.model.AuthDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationImpl implements Authentication {

    private final RestTemplate restTemplate;
    private final JwtValidationService jwtService;
    private static final String URL = "http://localhost:8999/api/v1/auth/auth";
    private static final String SECRET = "fe623bd733fc57bad3fe78db92b1016488e323912553695449d0d39cb833b13a";

    @Override
    public ResponseEntity<String> authenticate(AuthDetails authDetails) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuthDetails> auth = new HttpEntity<>(authDetails, headers);

        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, auth, String.class);

        return new ResponseEntity<>(response.getHeaders(), HttpStatus.OK);
    }

    @Override
    public Cookie createCookie(String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        return cookie;
    }

    @Override
    public Boolean setSecurityContext(String token) {
        String jwt = token.replace("Bearer ", "");
        var claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwt).getBody();

        String username = claims.getSubject();
        List<String> roles = new ArrayList<>();
        roles.add("USER");

        UserDetails userDetails = new User(username, "", roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        SecurityContextHolder.setContext(securityContext);
        return true;
    }
}