package org.example.commandsPackage;

import org.example.Utils.CollectionManager;
import org.example.Utils.PersonCreation;
import org.example.collectionClasses.*;

/**
 * Класс AddCommand реализует команду добавления нового элемента в коллекцию.
 * При выполнении команды пользователь вводит данные для создания объекта Person,
 * который затем добавляется в коллекцию.
 */
public class AddCommand implements Command {

    /**
     * Выполняет команду добавления нового элемента в коллекцию.
     * Создает объект Person через консольный ввод и добавляет его в коллекцию.
     */
    @Override
    public void execute() {
        Person person = PersonCreation.createPerson();
        if (person != null) {
            CollectionManager.add(person);
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
