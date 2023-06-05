package pro.vaidas.notebookuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.vaidas.notebookuser.repository.impl.RoleDAO;

import java.util.List;

public interface RoleRepository extends JpaRepository <RoleDAO, Integer> {
    @Query(value = "SELECT * FROM notebook_users.roles u WHERE u.user = ?1", nativeQuery = true)
    List<RoleDAO> findByUser(String userId);
}
