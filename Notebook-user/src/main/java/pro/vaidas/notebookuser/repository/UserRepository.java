package pro.vaidas.notebookuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.vaidas.notebookuser.repository.impl.UserDAO;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, UUID> {
    @Query(value = "SELECT * FROM notebook_users.users u WHERE u.email = ?1", nativeQuery = true)
    List<UserDAO> findByEmail(String email);
}
