package org.example.commandsPackage;

import org.example.Invoker;
import org.example.Utils.CollectionManager;
import org.example.Utils.PersonCreation;
import org.example.collectionClasses.Person;

/**
 * Класс UpdateCommand реализует команду обновления элемента коллекции по его id.
 * При выполнении команды пользователь вводит новые данные для объекта Person,
 * и элемент с указанным id обновляется в коллекции.
 */
public class UpdateCommand implements Command {

    /**
     * Выполняет команду обновления элемента коллекции по его id.
     * Если элемент с указанным id не существует, выводится сообщение об ошибке.
     * Иначе пользователь вводит новые данные для объекта Person, и элемент обновляется.
     */
    @Override
    public void execute() {
        Integer id;
        try {
            id = Integer.valueOf(Invoker.getArgument());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Некорректный формат числа для поля 'id'.");
            return;
        }

        Person person = CollectionManager.getPersonById(id);

        if (person == null) {
            System.out.println("Объекта класса Person с данным id не существует.");
        } else {
            Person newPerson = PersonCreation.createPerson();
            if (newPerson != null) {
                person.setName(newPerson.getName());
                person.setCoordinates(newPerson.getCoordinates());
                person.setHeight(newPerson.getHeight());
                person.setBirthday(newPerson.getBirthday());
                person.setEyeColor(newPerson.getEyeColor());
                person.setHairColor(newPerson.getHairColor());
                person.setLocation(newPerson.getLocation());
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
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}