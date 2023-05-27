package pro.vaidas.notebookserver.bootstrap;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class NoteCsvServiceImpl implements NoteCsvService{

    @Override
    public List<NoteCsvRecord> convert(File file){
        try {
            List<NoteCsvRecord> recordList = new CsvToBeanBuilder<NoteCsvRecord>(new FileReader(file))
                    .withType(NoteCsvRecord.class)
                    .build().parse();
            return recordList;
        } catch (FileNotFoundException e){throw new RuntimeException(e);}
    }
}
