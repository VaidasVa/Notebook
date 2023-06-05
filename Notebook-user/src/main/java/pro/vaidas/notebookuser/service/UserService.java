package pro.vaidas.notebookuser.service;

import pro.vaidas.notebookuser.model.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    void addUsers();

    List<User> findAllUsers();

    Optional<User> getUserById(UUID id);

    Optional<User> findUserByEmail(String email);

    Boolean userExistsByEmail(String email);

    void saveUser(User user) throws IOException, URISyntaxException;

    String setDefaultUserRole();

    void deleteUser(UUID id);
}
