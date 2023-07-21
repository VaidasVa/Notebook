package pro.vaidas.notebookuser.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.vaidas.notebookuser.mapper.UserMapper;
import pro.vaidas.notebookuser.model.User;
import pro.vaidas.notebookuser.repository.UserRepository;
import pro.vaidas.notebookuser.repository.impl.UserDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;

    private User user;
    private UserDAO userDAO;
    private List<User> userList;
    private List<UserDAO> userDaoList;

    @BeforeEach
    void setUp() {
        user = createUser();
        userDAO = createUserDAO();
        userList = getUserList(user);
        userDaoList = getUsersDAO(userDAO);
    }

    @Test
    @Disabled
    void findAllUsers() {
        when(repository.findAll()).thenReturn(userDaoList);
        when(mapper.userDAOToUser(userDAO)).thenReturn(user);
        when(service.findAllUsers()).thenReturn(userList);

        assertEquals(2, service.findAllUsers().size());
        verify(repository, times(1)).findAll();
}

    @Test
    void getUserById() {
    }

    @Test
    void findUserByEmail() {
    }

    @Test
    void userExistsByEmail() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void setDefaultUserRole() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void deleteByEmail() {
    }

    @Test
    void makeKafkaUser() {
    }

    private User createUser(){
        return User.builder()
                .name("user1")
                .notExpired(true)
                .notLocked(true)
                .credentialsNotExpired(true)
                .enabled(true)
                .build();
    }

    private UserDAO createUserDAO(){
        UserDAO userDAO = new UserDAO();
                userDAO.setName("user1");
                userDAO.setNotExpired(true);
                userDAO.setEnabled(true);
                userDAO.setCredentialsNotExpired(true);
                userDAO.setNotLocked(true);
        return userDAO;
    }

    private List<User> getUserList(User user){
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);
        return users;
    }

    private List<UserDAO> getUsersDAO(UserDAO userDAO){
        List<UserDAO> userDAOSlist = new ArrayList<>();
        userDAOSlist.add(userDAO);
        userDAOSlist.add(userDAO);
        return userDAOSlist;
    }

}

