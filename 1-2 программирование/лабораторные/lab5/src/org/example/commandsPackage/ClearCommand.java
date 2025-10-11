package org.example.commandsPackage;

import org.example.Utils.CollectionManager;

/**
 * Класс ClearCommand реализует команду очистки коллекции.
 * При выполнении команды все элементы коллекции удаляются.
 */
public class ClearCommand implements Command {

    /**
     * Выполняет команду очистки коллекции.
     * Удаляет все элементы из коллекции.
     */
    @Override
    public void execute() {
        CollectionManager.clearPersonSet();
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "clear : очистить коллекцию";
    }
}
