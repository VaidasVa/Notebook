package pro.vaidas.authserver.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.vaidas.authserver.config.RestTemplateConfig;
import pro.vaidas.authserver.model.User;

import java.util.Optional;

@Service
public class UserDetailsRetrievalServiceImpl implements UserDetailsRetrievalService {

    private final RestTemplate restTemplate;

    private static final String USER_PATH = "/id/{id}";

    public UserDetailsRetrievalServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public User getUserByEmail(String email) {
        ResponseEntity<User> response =
                restTemplate.getForEntity(USER_PATH, User.class, email);
        return response.getBody();
    }
}
