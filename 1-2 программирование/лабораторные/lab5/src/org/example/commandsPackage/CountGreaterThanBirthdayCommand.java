package org.example.commandsPackage;

import org.example.Utils.CollectionManager;
import org.example.Invoker;
import org.example.collectionClasses.Person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Класс CountGreaterThanBirthdayCommand реализует команду подсчета количества элементов,
 * у которых значение поля birthday больше заданной даты.
 * При выполнении команды пользователь вводит дату, и программа подсчитывает количество
 * объектов Person, у которых birthday больше этой даты.
 */
public class CountGreaterThanBirthdayCommand implements Command {

    /**
     * Выполняет команду подсчета количества элементов, у которых birthday больше заданной даты.
     * Если формат даты неверный, выводит сообщение об ошибке.
     */
    @Override
    public void execute() {
        try {
            String stringArgumentData = Invoker.getArgument();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.parse(stringArgumentData, dateFormatter);
            ZonedDateTime argumentData = localDate.atStartOfDay(ZoneId.systemDefault());

            int counter = 0;
            for (Person person : CollectionManager.getPersonSet()) {
                if (person.getBirthday().isAfter(argumentData)) {
                    counter += 1;
                }
            }

            System.out.println(counter);
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты. Введите дату в формате dd.MM.yyyy " +
                    "(например, 01.01.2001)");
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "count_greater_than_birthday birthday : вывести количество элементов, " +
                "значение поля birthday которых больше заданного";
    }
}