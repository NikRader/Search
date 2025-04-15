package QueryHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateSearchesTxtFile {
    public static void createFile(List<String> searches) {
        try {
            FileWriter writer = new FileWriter("UserSearches.txt");
            for (String str : searches) {
                writer.write(str+'\n');

            }

            //  writer.write("Это текст, который будет записан в файл.");
            writer.close();
            System.out.println("Файл успешно создан!");
        } catch (IOException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> list = List.of("Где найти агрегированный отчет по вкладам по Свердловской области?",
                "Какие налоговые льготы доступны пенсионерам?",
                "Как получить выписку из ЕГРН онлайн?",
                "Сроки подачи декларации 3-НДФЛ в 2024 году");

        createFile(list);

    }
}

