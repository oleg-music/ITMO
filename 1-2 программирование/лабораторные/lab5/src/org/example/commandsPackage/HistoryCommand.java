package org.example.commandsPackage;

import org.example.Utils.HistoryManager;

/**
 * Класс HistoryCommand реализует команду вывода истории выполненных команд.
 * При выполнении команды выводятся последние 9 выполненных команд (без их аргументов).
 */
public class HistoryCommand implements Command {

    /**
     * Выполняет команду вывода истории выполненных команд.
     * Выводит последние 9 команд, хранящихся в HistoryManager.
     */
    @Override
    public void execute() {
        for (String command : HistoryManager.getHistoryList()) {
            if (command != null) {
                System.out.println(command);
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
        return "history : вывести последние 9 команд (без их аргументов)";
    }
}
