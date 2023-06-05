package pro.vaidas.notebookuser.service;

import pro.vaidas.notebookuser.model.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    List<Role> getAllRoles();

    List<Role> getRoleByUserId(UUID id);

}
