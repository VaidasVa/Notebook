package pro.vaidas.authserver.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pro.vaidas.authserver.model.User;
import pro.vaidas.authserver.service.UserFromDbService;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository  {

    private final UserFromDbService service;

    public Optional<User> getUserByEmail(String email){
        return Optional.ofNullable(service.getUserDetailsFromRemoteDB(email));
    }
}
