package org.example.commandsPackage;

import java.util.Map;

/**
 * Класс HelpCommand реализует команду вывода справки по доступным командам.
 * При выполнении команды выводятся описания всех доступных команд.
 */
public class HelpCommand implements Command {

    /**
     * Карта команд, где ключ — название команды, а значение — объект команды.
     */
    private Map<String, Command> commands;

    /**
     * Конструктор класса HelpCommand.
     *
     * @param commands Карта команд, для которых нужно вывести справку.
     */
    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * Выполняет команду вывода справки по доступным командам.
     * Для каждой команды из карты выводится её описание.
     */
    @Override
    public void execute() {
        for (Command c : commands.values()) {
            System.out.println(c.descr());
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "help : вывести справку по доступным командам";
    }
}

