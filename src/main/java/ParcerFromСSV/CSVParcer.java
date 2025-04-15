package ParcerFromСSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class CSVParcer {
    public static int getInfoFromCSV() throws IOException {
        HashMap<String, String> dataMap = new HashMap<>();
        List<String> records = Files.readAllLines(Paths.get("reports.csv"));
        String separator = "\\|";

        int countRecord =1;

        for(String record: records){
            String[] updateRecord = record.split(separator);
           // System.out.println(countRecord+") updateRecord -> GUID: " + updateRecord[0]+"   name: "+ updateRecord[1]+"   discript: "+ updateRecord[2]);
            dataMap.put(updateRecord[2].trim(), updateRecord[0].trim());

            countRecord++;
        }
        return dataMap.size();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getInfoFromCSV());
    }
}
