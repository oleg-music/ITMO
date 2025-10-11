package org.example;

import org.example.Utils.Reader;
import org.example.xml.DOMReader;

/**
 * Главный класс приложения, который запускает программу и управляет основным потоком выполнения.
 * Инициализирует начальные данные из файла, создает объекты Person и обрабатывает команды пользователя.
 */
public class Main {

    /**
     * Объект Invoker, который отвечает за выполнение команд.
     */
    private static Invoker invoker = new Invoker();
    private static String fileName = "DefaultFileName.txt";

    /**
     * Точка входа в программу.
     * Загружает начальные данные из файла, создает объекты Person и переходит в режим обработки команд пользователя.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            fileName = args[0];
        }

        // fileName = "startFile.txt"; // DELETE THIS

        String filInString = Reader.readFileInString(fileName);
        if (filInString.trim() != "") {
            DOMReader.createPersonsFromXMLString(filInString);
        }

//         Person p1 = CollectionManager.getPersonById(1);
//         Person p2 = CollectionManager.getPersonById(2);
//         Person p3 = CollectionManager.getPersonById(3);
//         ArrayList<Person> persons = new ArrayList<>();
//         persons.add(p1);
//         persons.add(p2);
//         persons.add(p3);
//         System.out.println(persons);
//         Collections.sort(persons);
//         System.out.println(persons);

        while (Reader.hasNext()) {
            String line = Reader.nextLine();
            if (line.trim() != "") {
                invoker.invoke(line);
            }
        }
    }

    public static String getFileName() {
        return fileName;
    }
}