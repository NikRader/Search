package QueryHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueryHandler {

    public static final int MIN_WORD_LENGTH = 2;
    public static final int MAX_QUERY_WORDS = 10;
    // Сообщения для пользователя
    public static final String MESSAGE_EMPTY_QUERY = "Пустой запрос — введите ключевые слова.";
    public static final String MESSAGE_NO_RELEVANT_RESULTS = "Нет релевантных результатов по вашему запросу.";



    public static String checkQuery(String query) {
        if (query == null || query.trim().isEmpty()) {
            return MESSAGE_EMPTY_QUERY;
        }

        // Разбиваем запрос на слова, убираем лишние пробелы
        String[] words = query.trim().split("\\s+");

        // Фильтруем слишком короткие слова
        List<String> validWords = new ArrayList<>();
        for (String word : words) {
            if (word.length() >= MIN_WORD_LENGTH) {
                validWords.add(word);
            }
        }

        if (validWords.isEmpty()) {
            return MESSAGE_NO_RELEVANT_RESULTS;
        }

        // Ограничиваем количество слов
        if (validWords.size() > MAX_QUERY_WORDS) {
            validWords = validWords.subList(0, MAX_QUERY_WORDS);
        }

        // Собираем обратно в строку
        return String.join(" ", validWords);
    }


    public static void main(String[] args) {
        String query = "j j j j j  j j jj j j j j j  j j";
        System.out.println(checkQuery(query));
    }

}
