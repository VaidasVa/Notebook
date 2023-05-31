package pro.vaidas.notebookserver.business.service;

import pro.vaidas.notebookserver.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User findUserByEmail(String email);
}
