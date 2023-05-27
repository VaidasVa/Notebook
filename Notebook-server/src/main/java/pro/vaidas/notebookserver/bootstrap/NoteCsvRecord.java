package pro.vaidas.notebookserver.bootstrap;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteCsvRecord {

    @CsvBindByName
    private String id;

    @CsvBindByName
    private String title;

    @CsvBindByName
    private String content;

    @CsvBindByName
    private String created;
}
