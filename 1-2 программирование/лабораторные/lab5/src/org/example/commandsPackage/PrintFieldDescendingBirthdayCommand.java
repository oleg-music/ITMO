package org.example.commandsPackage;

import org.example.Utils.CollectionManager;
import org.example.collectionClasses.Person;

import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Класс PrintFieldDescendingBirthdayCommand реализует команду вывода значений поля birthday
 * всех элементов коллекции в порядке убывания.
 * При выполнении команды значения поля birthday сортируются по убыванию и выводятся в стандартный поток вывода.
 */
public class PrintFieldDescendingBirthdayCommand implements Command {

    /**
     * Выполняет команду вывода значений поля birthday всех элементов в порядке убывания.
     * Использует алгоритм сортировки пузырьком для сортировки дат.
     */
    @Override
    public void execute() {
        ArrayList<ZonedDateTime> listOfBirthdays = new ArrayList<>();

        for (Person person : CollectionManager.getPersonSet()) {
            listOfBirthdays.add(person.getBirthday());
        }

        int n = listOfBirthdays.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (listOfBirthdays.get(j).isBefore(listOfBirthdays.get(j + 1))) {
                    ZonedDateTime temp = listOfBirthdays.get(j);
                    listOfBirthdays.set(j, listOfBirthdays.get(j + 1));
                    listOfBirthdays.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        for (ZonedDateTime birthday : listOfBirthdays) {
            System.out.println(birthday);
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "print_field_descending_birthday : вывести значения поля birthday всех элементов в порядке убывания";
    }
}
