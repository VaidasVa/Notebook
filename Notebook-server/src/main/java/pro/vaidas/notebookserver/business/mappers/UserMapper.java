package pro.vaidas.notebookserver.business.mappers;

import org.mapstruct.Mapper;
import pro.vaidas.notebookserver.business.repository.impl.UserDAO;
import pro.vaidas.notebookserver.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDAO userToUserDAO(User user);
    User userDAOToUser(UserDAO userDAO);
}
