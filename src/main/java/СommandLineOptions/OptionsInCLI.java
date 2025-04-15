package СommandLineOptions;

import org.apache.commons.cli.*;

public class OptionsInCLI {
    public static void showOptions(String[] args) {  // Добавляем параметр args
        Options options = new Options();

        options.addOption(Option.builder()
                .longOpt("data")
                .hasArg()
                .argName("path-to-csv.csv")
                .desc("Путь до CSV-файла (разделитель '|')")
                .build());

        options.addOption(Option.builder()
                .longOpt("input-file")
                .hasArg()
                .argName("input-path-to-file.txt")
                .desc("Путь до входного файла")
                .build());

        options.addOption(Option.builder()
                .longOpt("output-file")
                .hasArg()
                .argName("output-path-to-file.json")
                .desc("Путь до JSON-файла с результатами")
                .build());

        // 2. Парсим аргументы
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);  // Передаем args

            // 3. Получаем значения параметров
            String csvPath = cmd.getOptionValue("data");
            String inputPath = cmd.getOptionValue("input-file");
            String outputPath = cmd.getOptionValue("output-file");

            // 4. Выводим информацию (или обрабатываем дальше)
            System.out.println("CSV файл: " + csvPath);
            System.out.println("Входной файл: " + inputPath);
            System.out.println("Выходной JSON: " + outputPath);

        } catch (ParseException e) {
            System.err.println("Ошибка парсинга: " + e.getMessage());
            new HelpFormatter().printHelp("java -jar app.jar", options);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        showOptions(args);  // Передаем args в метод
    }
}