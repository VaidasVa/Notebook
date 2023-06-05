package pro.vaidas.notebookuser.mapper;

import org.mapstruct.Mapper;
import pro.vaidas.notebookuser.model.Role;
import pro.vaidas.notebookuser.repository.impl.RoleDAO;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role roleDAOToRole (RoleDAO roleDAO);

    RoleDAO roleToRoleDAO (Role role);

}
