package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("airports.csv");
        Scanner scanner = new Scanner(System.in);
        String title;
        int numColum = 2;

        //Считывание параметра
        if (args.length != 0) {
            try {
                numColum = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage() + "\n Введенное значение не является числом");
                System.exit(0);
            }
        } else {
            System.out.println("При запуске приложение не был указан параметр номер колонки");
            System.exit(0);
        }
//      Заполнение Map значениями по указаной колонке
        Search.fillingMap(path, numColum);


//      Цикл поискS
        while (true) {
            System.out.println("Введите строку:");
            title = scanner.nextLine();
            if (title.equals("!quit"))
                break;
            Search.search(title.toLowerCase());
        }
    }
}