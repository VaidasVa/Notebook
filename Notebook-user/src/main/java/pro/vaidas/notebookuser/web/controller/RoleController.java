package pro.vaidas.notebookuser.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.vaidas.notebookuser.model.Role;
import pro.vaidas.notebookuser.service.RoleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/role")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> list = service.getAllRoles();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Role>> getAllByUserId(@PathVariable UUID id){
        List<Role> list = service.getRoleByUserId(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
