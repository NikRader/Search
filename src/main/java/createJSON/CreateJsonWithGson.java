package createJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateJsonWithGson {

    public static void main(String[] args) {
        String[] queries = {
                "Как получить отчет по суммам налогового вычета за 2023 год?",
                "Где найти агрегированный отчет по вкладам по Свердловской области?",
                "Какие налоговые льготы доступны пенсионерам?",
                "Как получить выписку из ЕГРН онлайн?",
                "Сроки подачи декларации 3-НДФЛ в 2024 году"
        };

        List<List<String>> resultIds = List.of(
                Arrays.asList("1a9f7664-9362-4f16-9a9d-7242c0cca6d3", "03066ad5-b213-420e-9273-f8018a3bc9b0"),
                Arrays.asList("442f1a87-3a93-4670-95a7-aed4a857dc0e"),
                Arrays.asList("b22a90c1-4462-4e1f-832b-b6f4e7b99ff3"),
                Arrays.asList("c73e4d15-6c47-4972-9c98-8c7e2b1190fc", "dbaf3e8f-7801-4c9e-9c82-3a7e2cbe49a9"),
                Arrays.asList("ae92c5f4-1244-4d69-a7b4-57fd9be9795d")
        );

        int[] times = {10, 10, 8, 9, 7};

        List<PackResponse> packResponses = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            packResponses.add(new PackResponse(queries[i], resultIds.get(i), times[i]));
        }

        PackJson packJason = new PackJson(100, packResponses);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("output_gson.json")) {
            gson.toJson(packJason, writer);
            System.out.println("Файл успешно создан: output_gson.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
