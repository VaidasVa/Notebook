package pro.vaidas.notebook.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.vaidas.notebook.business.repository.impl.NoteDAO;
import pro.vaidas.notebook.model.Note;

import java.util.UUID;

public interface NoteRepository extends JpaRepository<NoteDAO, UUID> {
}
