package org.example.commandsPackage;

import org.example.Utils.CollectionManager;
import org.example.Invoker;
import org.example.collectionClasses.Person;

/**
 * Класс RemoveByIdCommand реализует команду удаления элемента из коллекции по его id.
 * При выполнении команды элемент с указанным id удаляется из коллекции, если он существует.
 */
public class RemoveByIdCommand implements Command {

    /**
     * Выполняет команду удаления элемента из коллекции по его id.
     * Если элемент с указанным id не существует, выводится сообщение об ошибке.
     */
    @Override
    public void execute() {
        Integer id = Integer.valueOf(Invoker.getArgument());

        Person person = CollectionManager.getPersonById(id);

        if (person == null) {
            System.out.println("Объекта класса Person с данным id не существует.");
        } else {
            CollectionManager.removePerson(person);
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
