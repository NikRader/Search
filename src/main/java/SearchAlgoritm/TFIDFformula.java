package SearchAlgoritm;

import java.util.List;

public class TFIDFformula {

    public double tf(List<String> doc, String term) {
        if (doc == null || doc.isEmpty()) return 0.0;

        double count = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                count++;
        }
        return count / doc.size();
    }

    public double idf(List<List<String>> docs, String term) {
        if (docs == null || docs.isEmpty()) return 0.0;

        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        // Добавляем 1 к n и знаменателю, чтобы избежать деления на ноль
        return Math.log((docs.size() + 1) / (n + 1)) + 1;
    }

    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);
    }
}