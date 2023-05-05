package pro.vaidas.notebookserver.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.vaidas.notebookserver.business.repository.impl.UserDAO;


import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDAO, UUID> {
}
