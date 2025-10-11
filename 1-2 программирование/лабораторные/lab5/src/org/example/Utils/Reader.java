package org.example.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс Reader предоставляет методы для чтения данных из консоли и файлов.
 * Поддерживает чтение строк из файла в виде одной строки или списка строк.
 */
public class Reader {

    /**
     * Сканер для чтения данных из консоли.
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Проверяет, есть ли следующая строка в потоке ввода.
     *
     * @return true, если есть следующая строка, иначе false.
     */
    public static boolean hasNext() {
        return sc.hasNext();
    }

    /**
     * Читает следующую строку из потока ввода.
     *
     * @return Считанная строка.
     */
    public static String nextLine() {
        return sc.nextLine();
    }

    /**
     * Читает содержимое файла и возвращает его в виде одной строки.
     * Если файл не существует, выводит сообщение об ошибке.
     *
     * @param fileName Имя файла для чтения.
     * @return Содержимое файла в виде строки. Если файл не существует, возвращает пустую строку.
     */
    public static String readFileInString(String fileName) {
        String fileInString = "";
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                fileInString += scanner.nextLine().trim();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файла для считывания данных нет.");
        }
        return fileInString;
    }

    /**
     * Читает содержимое файла и возвращает его в виде списка строк.
     * Если файл не существует, выводит сообщение об ошибке.
     *
     * @param fileName Имя файла для чтения.
     * @return Список строк, содержащих данные из файла. Если файл не существует, возвращает пустой список.
     */
    public static ArrayList<String> readFileInStrings(String fileName) {
        ArrayList<String> listOfFileStrings = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                listOfFileStrings.add(scanner.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не существует.");
        }
        return listOfFileStrings;
    }
}
