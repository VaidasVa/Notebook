package pro.vaidas.notebookuser.mapper;

import org.mapstruct.Mapper;
import pro.vaidas.notebookuser.model.User;
import pro.vaidas.notebookuser.repository.impl.UserDAO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDAOToUser (UserDAO userDAO);
    UserDAO userToUserDAO (User user);
}