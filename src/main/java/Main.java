import ParcerFromСSV.CSVParcer;
import QueryHandler.QueryHandler;
import QueryHandler.SearchesScanner;
import QueryHandler.TextFile;
import SearchAlgoritm.RelevantSearch;
import createJSON.CreateJsonWithGson;
import createJSON.PackJson;
import createJSON.PackResponse;
import СommandLineOptions.OptionsCLI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static long programStartTime;
    private static int initTime;

    public static void main(String[] args) throws IOException {
        programStartTime = System.currentTimeMillis();
        // Параметры консоли
        OptionsCLI.showOptions(args);

        // Парсинг строк из CSV-файла
        List<List<String>> info = CSVParcer.getInfoFromCSV();
        // Пользовательский ввод
        List<String> searches = SearchesScanner.SearchesScanner();
        initTime = (int) (System.currentTimeMillis() - programStartTime);
        // Обработка запросов пользователя
        List<String> goodSearches = QueryHandler.checkQuery(searches);

        List<String> guidsList = info.get(0);
        List<String> discriptsList = info.get(1);
        TextFile.create(goodSearches);

        List<PackResponse> packResponseList = new ArrayList<>();
        for (String search : goodSearches) {
            PackResponse packResponse = RelevantSearch.relevantSearch(discriptsList, guidsList, search);
            packResponseList.add(packResponse);
        }
        PackJson packJson = new PackJson(initTime, packResponseList);
        CreateJsonWithGson.create(packJson);

    }
}
