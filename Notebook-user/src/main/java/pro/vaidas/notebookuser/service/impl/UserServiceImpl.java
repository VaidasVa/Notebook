package pro.vaidas.notebookuser.service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.vaidas.notebookuser.bootstrap.AddUser;
import pro.vaidas.notebookuser.mapper.UserMapper;
import pro.vaidas.notebookuser.model.KafkaMessageFromUser;
import pro.vaidas.notebookuser.model.Role;
import pro.vaidas.notebookuser.model.User;
import pro.vaidas.notebookuser.repository.UserRepository;
import pro.vaidas.notebookuser.service.MailingService;
import pro.vaidas.notebookuser.service.RoleService;
import pro.vaidas.notebookuser.service.UserService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Data
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository repository;

    @Autowired
    private MailingService mail;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private RoleService roleService;

    private AddUser addUser;

    @Override
    public void addUsers() {
        addUser.addNewUsersIfEmpty();
    }

    @Override
    public List<User> findAllUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::userDAOToUser)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return repository.findById(id).map(mapper::userDAOToUser);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        List<User> list = repository.findByEmail(email)
                .stream()
                .map(found -> mapper.userDAOToUser(found))
                .toList();

        User user;
        if (list.isEmpty()) {
            user = null;
        } else {
            user = list.get(0);
        }

        if (user == null) {
            return Optional.empty();
        } else {
            return Optional.of(user);
        }
    }


    @Override
    public Boolean userExistsByEmail(String email) {
        return !repository.findByEmail(email).isEmpty();
    }

    @Override
    public void saveUser(User user) throws IOException, URISyntaxException {
        // Without setting UUID here, the roles would not know which ID to set in joinCollumn
        user.setId(UUID.randomUUID());
        if (user.getRole().toString().toLowerCase().contains("admin")) {
            List<Role> list = new ArrayList<>();
            list.add(Role.builder().id(new Random().nextInt()).role("ADMIN").build());
            user.setRole(list);
            // Admin automatically activated
            user.setActivated(true);
            repository.save(mapper.userToUserDAO(user));
        } else {
            List<Role> list = new ArrayList<>();
            list.add(Role.builder().id(new Random().nextInt()).role("USER").build());
            user.setRole(list);
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
    public KafkaMessageFromUser makeKafkaUser(User user, String event){
        return KafkaMessageFromUser.builder()
                .email(user.getEmail())
                .name(user.getName())
                .eventType(event)
                .build();
    }

}
