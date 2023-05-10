package pro.vaidas.notebookserver.business.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.vaidas.notebookserver.business.mappers.UserMapper;
import pro.vaidas.notebookserver.business.repository.UserRepository;
import pro.vaidas.notebookserver.business.service.UserService;
import pro.vaidas.notebookserver.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    @Override
    public List<User> getAllUsers(){
        return repository.findAll()
                .stream()
                .map(mapper::userDAOToUser)
                .collect(Collectors.toList());
    }

    @Override
    public User saveUser(User user){
        return mapper.userDAOToUser(repository
                .save(mapper.userToUserDAO(user)));
    }

    @Override
    public User findUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}
