package pro.vaidas.notebook.business.mappers;

import org.mapstruct.Mapper;
import pro.vaidas.notebook.business.repository.impl.UserDAO;
import pro.vaidas.notebook.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDAO userToUserDAO(User user);
    User userDAOToUser(UserDAO userDAO);
}
