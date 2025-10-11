package org.example.commandsPackage;

import org.example.Utils.CollectionManager;
import org.example.collectionClasses.Person;
import org.example.xml.DOMWriter;

import java.util.HashSet;

/**
 * Класс SaveCommand реализует команду сохранения коллекции в файл.
 * При выполнении команды данные коллекции сохраняются в XML-формате в файл "startFile.txt".
 */
public class SaveCommand implements Command {

    /**
     * Выполняет команду сохранения коллекции в файл.
     * Данные коллекции записываются в файл "startFile.txt" в формате XML.
     * Если происходит ошибка ввода-вывода, выводится стек вызовов исключения.
     */
    @Override
    public void execute() {
        HashSet<Person> personSet = CollectionManager.getPersonSet();
        DOMWriter.writeCollectionInXML(personSet);
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "save : сохранить коллекцию в файл";
    }
}

