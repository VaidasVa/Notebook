package pro.vaidas.authserver.service;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.vaidas.authserver.model.User;

@Service
@RequiredArgsConstructor
public class UserFromDbService {

    private final RestTemplate restTemplate;

    @Value("${custom.fetchUserUri}")
    private String URI;

    public User getUserDetailsFromRemoteDB(String email) {
        String url = URI + email;
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        if (response.getStatusCode().is5xxServerError()) {
            throw new NotFoundException();
        } else {
            return response.getBody();
        }
    }

    public Boolean doesUserExist(String email){
        String url = URI + email;
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        return response.hasBody();
    }
}