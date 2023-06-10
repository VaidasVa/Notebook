package pro.vaidas.notebookuser.service.impl;

import org.springframework.stereotype.Service;
import pro.vaidas.notebookuser.mapper.RoleMapper;
import pro.vaidas.notebookuser.model.Role;
import pro.vaidas.notebookuser.repository.RoleRepository;
import pro.vaidas.notebookuser.service.RoleService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Role> getAllRoles() {
        return repository.findAll()
                .stream()
                .map(mapper::roleDAOToRole)
                .collect(Collectors.toList());
    }

    @Override
    public List<Role> getRoleByUserId(UUID id) {
        System.out.println("In service : " + repository.findByUser(id.toString()));
        return repository.findByUser(id.toString())
                .stream()
                .map(mapper::roleDAOToRole)
                .collect(Collectors.toList());
    }
}
