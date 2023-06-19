package pro.vaidas.notebookuser.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.vaidas.notebookuser.mapper.UserMapper;
import pro.vaidas.notebookuser.model.KafkaMessageFromUser;
import pro.vaidas.notebookuser.model.Role;
import pro.vaidas.notebookuser.model.User;
import pro.vaidas.notebookuser.repository.UserRepository;
import pro.vaidas.notebookuser.service.UserService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAllUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::userDAOToUser)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<User> getUserById(UUID id) {
        return repository
                .findById(id)
                .map(mapper::userDAOToUser);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return repository
                .findUserDAOByEmail(email)
                .map(mapper::userDAOToUser);
    }

    @Override
    public Boolean userExistsByEmail(String email) {
        return repository.findUserDAOByEmail(email).isPresent();
    }

    @Override
    public void saveUser(User user) throws IOException, URISyntaxException {
        user.setId(UUID.randomUUID());
        if (user.getRole().equals(Role.ADMIN)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Role.ADMIN);
            user.setCredentialsNotExpired(true);
            user.setEnabled(true);
            user.setNotExpired(true);
            user.setNotLocked(true);
            repository.save(mapper.userToUserDAO(user));
        } else {
            user.setRole(Role.USER);
            user.setCredentialsNotExpired(true);
            user.setEnabled(true);
            user.setNotExpired(true);
            user.setNotLocked(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.save(mapper.userToUserDAO(user));
        }
    }

    @Override
    public String setDefaultUserRole() {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByEmail(String email) {
        Optional<User> user = findUserByEmail(email);
        user.ifPresent(foundUser -> repository.deleteById(foundUser.getId()));
    }

    @Override
    public KafkaMessageFromUser makeKafkaUser(User user, String event){
        return KafkaMessageFromUser.builder()
                .email(user.getEmail())
                .name(user.getName())
                .eventType(event)
                .build();
    }
}
