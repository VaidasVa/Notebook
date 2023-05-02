package pro.vaidas.notebook.business.service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.vaidas.notebook.business.mappers.UserMapper;
import pro.vaidas.notebook.business.repository.UserRepository;
import pro.vaidas.notebook.business.service.UserService;
import pro.vaidas.notebook.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

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

}
