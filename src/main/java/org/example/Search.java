package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Search {
    private static final Map<String, String> map = new HashMap<>();
    private static List<String> filesStr;

    //   Метод с помощью которого заполняется Map где ключ это номер строки а значением является значия выбраной колонки
    public static void fillingMap(Path path, int numColumn) {
        if (numColumn < 1 || numColumn > 14) {
            System.out.println("Указаного номера колонки не существует");
            System.exit(0);
        }
        try (Stream<String> streamFiles = Files.lines(path)){
            filesStr = streamFiles .collect(Collectors.toUnmodifiableList());
            filesStr.forEach(s -> {
                String[] strArr = s.split("(\",\")|(,\")|(\",)|(,)");
                map.put(strArr[0], strArr[numColumn - 1].toLowerCase());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //   реализация поиска и выведение строк
    public static void search(String value) {
        long time = System.currentTimeMillis();
        if (map.isEmpty())
            return;
        System.out.print("строк: " +
                map.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().startsWith(value))
                        .map(Map.Entry::getKey)
                        .peek(d ->filesStr.stream().filter(fs -> fs.startsWith(d)).findFirst().ifPresent(System.out::println))
                        .count()
        );
        System.out.println(" поиск: " + (System.currentTimeMillis() - time) + " мс");
    }
}
