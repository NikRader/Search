package SearchAlgoritm;

import createJSON.PackJson;
import createJSON.PackResponse;

import java.util.*;
import java.util.stream.Collectors;

public class RelevantSearch {

    public static PackResponse relevantSearch(List<String> descriptions, List<String> guidsList, String search) {
        // Проверка входных параметров
        if (descriptions == null || guidsList == null || search == null) {
            throw new IllegalArgumentException("Input parameters cannot be null");
        }

        TFIDFformula tfidf = new TFIDFformula();
        long startTime = System.currentTimeMillis();
        // Преобразуем описания в список токенов
        List<List<String>> docs = descriptions.stream()
                .filter(Objects::nonNull)  // Фильтруем null-описания
                .map(line -> Arrays.asList(line.toLowerCase().split("\\s+")))
                .collect(Collectors.toList());

        // Токены поискового запроса
        List<String> searchTerms = Arrays.stream(search.toLowerCase().split("\\s+"))
                .filter(term -> !term.isEmpty())
                .collect(Collectors.toList());

        // Ранжирование документов
        Map<Integer, Double> docScores = new HashMap<>();
        for (int i = 0; i < docs.size(); i++) {
            List<String> doc = docs.get(i);
            double score = 0.0;
            for (String term : searchTerms) {
                score += tfidf.tfIdf(doc, docs, term);
            }
            if (score > 0.0) {
                docScores.put(i, score);
            }
        }
        long endTime = System.currentTimeMillis();
        long time =  (endTime - startTime);
        // Сортировка и выбор топ-3 результатов
        List<String> result = docScores.entrySet().stream()
                .sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
                .limit(3)
                .map(entry -> {
                    int index = entry.getKey();
                    return index < guidsList.size() ? guidsList.get(index) : null;
                })
                .filter(Objects::nonNull)  // Фильтруем null GUIDs
                .collect(Collectors.toList());
        PackResponse pack = new PackResponse(search,result, (int) time);
        return pack;
    }
}