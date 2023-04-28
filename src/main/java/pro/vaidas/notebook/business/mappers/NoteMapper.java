package pro.vaidas.notebook.business.mappers;

import org.mapstruct.Mapper;
import pro.vaidas.notebook.business.repository.impl.NoteDAO;
import pro.vaidas.notebook.model.Note;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDAO noteToNoteDAO(Note note);
    Note noteDAOToNote(NoteDAO noteDAO);

}