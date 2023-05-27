package pro.vaidas.notebookserver.bootstrap;

import java.io.File;
import java.util.List;

public interface NoteCsvService {
    List<NoteCsvRecord> convert(File file);
}
