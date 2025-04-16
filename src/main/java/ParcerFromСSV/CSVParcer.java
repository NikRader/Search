package ParcerFromСSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CSVParcer {
    public static List<List<String>> getInfoFromCSV() throws IOException {
        List<String> guidList = new ArrayList<>();
        List<String> discriptionList = new ArrayList<>();
        List<String> records = Files.readAllLines(Paths.get("reports.csv"));
        String separator = "\\|";
        for(String record: records){
            String[] updateRecord = record.split(separator);
            discriptionList.add(updateRecord[2].trim());
            guidList.add(updateRecord[0].trim());
        }
        return List.of(
                guidList,
                discriptionList
        );
    }

}
