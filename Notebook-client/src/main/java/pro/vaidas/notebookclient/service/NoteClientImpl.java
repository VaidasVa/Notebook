package pro.vaidas.notebookclient.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pro.vaidas.notebookclient.model.Note;
import pro.vaidas.notebookclient.wrapper.PageableResponse;

import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoteClientImpl implements NoteClient {

    private final RestTemplate restTemplate;
    private CircuitBreakerFactory circuitBreakerFactory;

    @Value("${custom.notesPath}")
    private String notesPath;
    @Value("${custom.notesPathWithId}")
    private String notesPathById;


    @Override
    public PageableResponse<Note> getNotes(String content,
                                           Integer pageNumber,
                                           Integer pageSize) {

        String text  = Optional.ofNullable(content).orElse("");
        Integer num  = Optional.ofNullable(pageNumber).orElse(0);
        Integer size = Optional.ofNullable(pageSize).orElse(20);
        String uri = notesPath + "?pageSize=" + size +
                "&pageNumber="+ num + "&content=" + text;

        ResponseEntity<PageableResponse<Note>> response =
                restTemplate.exchange((uri), HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() { });
        return response.getBody();
    }

    @Override
    public Note getNoteById(UUID id) {
        ResponseEntity<Note> response = restTemplate.getForEntity(notesPathById, Note.class, id);
        log.info("Response received from Notebook Server: " + response);
        return response.getBody();
    }

    @Override
    public HttpStatus addNote(Note note) {
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(notesPath, note, HttpStatus.class);
        log.info("Response received from Notebook Server: " + response);
        return response.getBody();
    }

    @Override
    public void updateNote(UUID id, Note note) {
        restTemplate.put(notesPathById, note, id);
    }

    @Override
    public void deleteNote(UUID id) {
        restTemplate.delete(notesPathById, id);
    }
}
