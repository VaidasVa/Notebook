package pro.vaidas.notebookclient.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.vaidas.notebookclient.model.Note;
import pro.vaidas.notebookclient.wrapper.PageableResponse;

import java.util.UUID;

@Service
@Log4j2
public class NoteClientImpl implements NoteClient {

    private final RestTemplate restTemplate;

    @Value("${custom.notesPath}")
    private String notesPath;
    @Value("${custom.notesPathWithId}")
    private String notesPathById;

    public NoteClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public PageableResponse<Note> getNotes(String title, String content, Integer pageNumber, Integer pageSize) {

        String size;
        String number;
        String text;

        if (pageSize != null){ size = "pageSize="+pageSize;}
        else {size = "";}
        if (pageNumber != null) {number = "pageNumber="+pageNumber;}
        else {number = "";}
        if (content != null) { text = "content=" + content;}
        else {text = "";}

        String url = notesPath + "?" + size + "&" + number + "&" + text;

        ResponseEntity<PageableResponse<Note>> response =
                restTemplate.exchange((url), HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {                        });
        return response.getBody();
    }

    @Override
    @LoadBalanced
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
