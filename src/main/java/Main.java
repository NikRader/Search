import QueryHandler.SearchesScanner;
import SearchAlgoritm.RelevantSearch;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать!");
        List<String> searches =
        SearchesScanner.SearchesScanner();
        RelevantSearch.relevantSearch(searches,);
    }
}
