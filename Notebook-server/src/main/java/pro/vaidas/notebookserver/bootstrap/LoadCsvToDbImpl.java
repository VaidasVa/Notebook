package pro.vaidas.notebookserver.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import pro.vaidas.notebookserver.business.repository.NoteRepository;
import pro.vaidas.notebookserver.business.repository.impl.NoteDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoadCsvToDbImpl implements CommandLineRunner, LoadCsvToDb {

    public final NoteRepository repository;
    private final NoteCsvService service;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadCSVdata();
    }

    private void loadCSVdata() throws FileNotFoundException {
//        System.out.println("I'm in...");
        if (repository.count()<20){
            File file = ResourceUtils.getFile("classpath:csv/myFile.csv");
            List<NoteCsvRecord> recordList = service.convert(file);

            recordList.forEach(record -> {
                repository.save(NoteDAO.builder()
                            .title(record.getTitle())
                            .content(record.getContent())
                        .build());
            });
        }
    }
}
