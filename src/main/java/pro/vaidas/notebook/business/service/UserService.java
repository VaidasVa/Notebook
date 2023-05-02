package pro.vaidas.notebook.business.service;

import pro.vaidas.notebook.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User saveUser(User user);
}
