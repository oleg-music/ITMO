package org.example.commandsPackage;

import org.example.Utils.CollectionManager;
import org.example.Utils.PersonCreation;
import org.example.collectionClasses.Person;

import java.util.HashSet;

/**
 * Класс RemoveGreaterCommand реализует команду удаления из коллекции всех элементов,
 * значение поля height которых превышает значение поля height заданного элемента.
 * При выполнении команды пользователь вводит данные для создания объекта Person,
 * после чего из коллекции удаляются все элементы, у которых height больше, чем у заданного.
 */
public class RemoveGreaterCommand implements Command {

    /**
     * Выполняет команду удаления из коллекции всех элементов, превышающих заданный.
     * Создает объект Person через консольный ввод и удаляет из коллекции все элементы,
     * у которых значение поля height больше, чем у заданного.
     */
    @Override
    public void execute() {
        Person givenPerson = PersonCreation.createPerson();
        if (givenPerson != null) {
            HashSet<Person> personToRemove = new HashSet<>();

            for (Person person : CollectionManager.getPersonSet()) {
                if (person.getHeight() != null && givenPerson.getHeight() != null) {
                    if (person.getHeight() > givenPerson.getHeight()) {
                        personToRemove.add(person);
                    }
                }
            }

            for (Person person : personToRemove) {
                CollectionManager.removePerson(person);
            }
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    }
}
