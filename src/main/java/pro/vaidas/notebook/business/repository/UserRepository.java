package pro.vaidas.notebook.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.vaidas.notebook.business.repository.impl.NoteDAO;
import pro.vaidas.notebook.business.repository.impl.UserDAO;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDAO, UUID> {
}
