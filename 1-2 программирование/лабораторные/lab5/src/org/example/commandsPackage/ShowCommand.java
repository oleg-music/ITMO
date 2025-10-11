package org.example.commandsPackage;

import org.example.Utils.CollectionManager;
import org.example.collectionClasses.Person;

/**
 * Класс ShowCommand реализует команду вывода всех элементов коллекции в строковом представлении.
 * При выполнении команды все элементы коллекции выводятся в стандартный поток вывода.
 */
public class ShowCommand implements Command {

    /**
     * Выполняет команду вывода всех элементов коллекции в строковом представлении.
     * Для каждого элемента коллекции вызывается метод toString(), и результат выводится в консоль.
     */
    @Override
    public void execute() {
        for (Person person : CollectionManager.getPersonSet()) {
            System.out.println(person);
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}