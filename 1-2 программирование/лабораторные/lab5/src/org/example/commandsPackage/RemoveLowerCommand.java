package org.example.commandsPackage;

import org.example.Utils.CollectionManager;
import org.example.Utils.PersonCreation;
import org.example.collectionClasses.Person;

import java.util.HashSet;

/**
 * Класс RemoveLowerCommand реализует команду удаления из коллекции всех элементов,
 * значение поля height которых меньше значения поля height заданного элемента.
 * При выполнении команды пользователь вводит данные для создания объекта Person,
 * после чего из коллекции удаляются все элементы, у которых height меньше, чем у заданного.
 */
public class RemoveLowerCommand implements Command {

    /**
     * Выполняет команду удаления из коллекции всех элементов, меньших, чем заданный.
     * Создает объект Person через консольный ввод и удаляет из коллекции все элементы,
     * у которых значение поля height меньше, чем у заданного.
     */
    @Override
    public void execute() {
        Person givenPerson = PersonCreation.createPerson();
        if (givenPerson != null) {
            HashSet<Person> personToRemove = new HashSet<>();

            for (Person person : CollectionManager.getPersonSet()) {
                if (person.getHeight() != null && givenPerson.getHeight() != null) {
                    if (person.getHeight() < givenPerson.getHeight()) {
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
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}