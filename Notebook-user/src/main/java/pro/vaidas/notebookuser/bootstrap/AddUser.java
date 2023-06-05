package pro.vaidas.notebookuser.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pro.vaidas.notebookuser.repository.RoleRepository;
import pro.vaidas.notebookuser.repository.UserRepository;
import pro.vaidas.notebookuser.repository.impl.RoleDAO;
import pro.vaidas.notebookuser.repository.impl.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AddUser {

    @Autowired
    private UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    @Bean
    public void addNewUsersIfEmpty(){

        if (repository.count() == 0) {
            RoleDAO role1 = RoleDAO.builder().id(1).role("USER").build();
            RoleDAO role2 = RoleDAO.builder().id(2).role("USER").build();
            RoleDAO role3 = RoleDAO.builder().id(3).role("USER").build();
            RoleDAO role4 = RoleDAO.builder().id(4).role("USER").build();
//            RoleDAO role2 = RoleDAO.builder().id(2).role("ADMIN").build();
//
//            roleRepository.save(role1);
//            roleRepository.save(role2);

            List<RoleDAO> roles1 = new ArrayList<>();
            List<RoleDAO> roles2 = new ArrayList<>();
            List<RoleDAO> roles3 = new ArrayList<>();
            List<RoleDAO> roles4 = new ArrayList<>();
            roles1.add(role1);
            roles2.add(role2);
            roles3.add(role3);
            roles4.add(role4);

            repository.save(
                    UserDAO.builder()
                            .id(UUID.randomUUID())
                            .name("a")
                            .email("a")
                            .password("a")
                            .activated(false)
                            .role(roles1)
                            .build());

            repository.save(
                    UserDAO.builder()
                            .id(UUID.randomUUID())
                            .name("b")
                            .email("b")
                            .password("b")
                            .activated(false)
                            .role(roles2)
                            .build());

            repository.save(
                    UserDAO.builder()
                            .id(UUID.randomUUID())
                            .name("c")
                            .email("c")
                            .password("c")
                            .activated(false)
                            .role(roles3)
                            .build());

            repository.save(
                    UserDAO.builder()
                            .id(UUID.randomUUID())
                            .name("d")
                            .email("d")
                            .password("d")
                            .activated(false)
                            .role(roles4)
                            .build());
        }
        else {}
    }
}
