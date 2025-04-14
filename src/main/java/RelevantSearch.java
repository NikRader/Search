import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelevantSearch {
    public static void main(String[] args) {
        TFIDFformula tfidf = new TFIDFformula();
        
        // Документы (каждая строка — документ)
        List<String> rawDocs = Arrays.asList(
                "кошка сидит на окне",
                "собака гуляет в парке",
                "на окне лежит кот",
                "птица летит по небу"
        );

        // Разбиваем документы на слова
        List<List<String>> docs = rawDocs.stream()
                .map(line -> Arrays.asList(line.toLowerCase().split("\\s+")))
                .collect(Collectors.toList());

        // Запрос пользователя
        String query = "кот на окне";
        List<String> queryTerms = Arrays.asList(query.toLowerCase().split("\\s+"));

        // Ранжирование документов по суммарному TF-IDF по словам из запроса
        Map<Integer, Double> docScores = new HashMap<>();
        for (int i = 0; i < docs.size(); i++) {
            List<String> doc = docs.get(i);
            double score = 0.0;
            for (String term : queryTerms) {
                score += tfidf.tfIdf(doc, docs, term);
            }
            docScores.put(i, score);
        }

        // Сортировка по релевантности (по убыванию)
        List<Map.Entry<Integer, Double>> sortedDocs = docScores.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .collect(Collectors.toList());

        // Вывод результатов
        System.out.println(" Запрос: " + query);
        System.out.println("Релевантные строки:");
        for (Map.Entry<Integer, Double> entry : sortedDocs) {
            int index = entry.getKey();
            double score = entry.getValue();
            System.out.printf(" → \"%s\" (score: %.4f)%n", rawDocs.get(index), score);
        }
    }
}

