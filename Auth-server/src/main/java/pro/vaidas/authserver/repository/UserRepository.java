package pro.vaidas.authserver.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pro.vaidas.authserver.model.User;
import pro.vaidas.authserver.service.UserFromDbService;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository {

    private final UserFromDbService service;

    public Optional<User> getUserByEmail(String email){
        return Optional.ofNullable(service.getUserDetailsFromRemoteDB(email));
    }

//    public Optional<User> getUser(String email){
//        Optional<User> user = getUserByEmail(email);
//        if (user.isPresent()){
//            User user1 =  User.builder()
//                    .id(user.get().getId())
//                    .name(user.get().getName())
//                    .email(user.get().getEmail())
//                    .password(user.get().getPassword())
//                    .role(user.get().getRole())
//                    .created(user.get().getCreated())
//                    .notExpired(user.get().isNotExpired())
//                    .notLocked(user.get().isNotLocked())
//                    .credentialsNotExpired(user.get().isCredentialsNotExpired())
//                    .enabled(user.get().isEnabled())
//                    .build();
//            return Optional.ofNullable(user1);
//
//        }
//        else { return null;}
//    }
}
