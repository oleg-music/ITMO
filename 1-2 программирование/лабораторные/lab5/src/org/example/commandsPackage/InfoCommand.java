package org.example.commandsPackage;

import org.example.Utils.CollectionManager;

/**
 * Класс InfoCommand реализует команду вывода информации о коллекции.
 * При выполнении команды выводятся тип коллекции, дата инициализации и количество элементов.
 */
public class InfoCommand implements Command {

    /**
     * Выполняет команду вывода информации о коллекции.
     * Выводит тип коллекции, дату инициализации и количество элементов.
     */
    @Override
    public void execute() {
        System.out.print("Тип коллекции: ");
        System.out.println(CollectionManager.getPersonSet().getClass());
        System.out.print("Время инициализации: ");
        System.out.println(CollectionManager.getInitializationTime());
        System.out.print("Количество элементов: ");
        System.out.println(CollectionManager.getPersonSet().size());
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "info : вывести в стандартный поток вывода информацию о коллекции " +
                "(тип, дата инициализации, количество элементов и т.д.)";
    }
}
