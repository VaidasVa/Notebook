package pro.vaidas.notebookuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.vaidas.notebookuser.repository.impl.UserDAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, UUID> {
    Optional<UserDAO> findUserDAOByEmail(String email);
}
