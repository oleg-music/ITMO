package org.example.commandsPackage;

import org.example.Utils.CollectionManager;
import org.example.collectionClasses.EyeColor;
import org.example.collectionClasses.Person;

import java.util.HashSet;

/**
 * Класс PrintUniqueEyeColorCommand реализует команду вывода уникальных значений поля eyeColor
 * всех элементов коллекции.
 * При выполнении команды выводятся все уникальные значения поля eyeColor, присутствующие в коллекции.
 */
public class PrintUniqueEyeColorCommand implements Command {

    /**
     * Выполняет команду вывода уникальных значений поля eyeColor.
     * Использует HashSet для хранения уникальных значений.
     */
    @Override
    public void execute() {
        HashSet<EyeColor> uniqueEyeColors = new HashSet<>();

        for (Person person : CollectionManager.getPersonSet()) {
            uniqueEyeColors.add(person.getEyeColor());
        }
        if (uniqueEyeColors.contains(null)) {
            uniqueEyeColors.remove(null);
        }
        System.out.println(uniqueEyeColors);
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "print_unique_eye_color : вывести уникальные значения поля eyeColor всех элементов в коллекции";
    }
}
