package pro.vaidas.authserver.service;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.vaidas.authserver.model.User;

@Service
@RequiredArgsConstructor
public class UserFromDbService {

    private final RestTemplate restTemplate;

    public User getUserDetailsFromRemoteDB(String email) {
        String url = "http://localhost:8080/api/v1/user/email/" + email;
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        if (response.getStatusCode().is5xxServerError()) {
            throw new NotFoundException();
        } else {
            return response.getBody();
        }
    }
}