package pro.vaidas.notebookuser.service;

import pro.vaidas.notebookuser.model.KafkaMessageFromUser;
import pro.vaidas.notebookuser.model.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService {

    List<User> findAllUsers();

    Optional<User> getUserById(UUID id);

    Optional<User> findUserByEmail(String email);

    Boolean userExistsByEmail(String email);

    void saveUser(User user) throws IOException, URISyntaxException;

    String setDefaultUserRole();

    void deleteUser(UUID id);

    void deleteByEmail(String email);

    KafkaMessageFromUser makeKafkaUser(User user, String event);
}
