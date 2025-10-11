package org.example.commandsPackage;

/**
 * Класс ExitCommand реализует команду завершения программы.
 * При выполнении команды программа завершает свою работу без сохранения данных в файл.
 */
public class ExitCommand implements Command {

    /**
     * Выполняет команду завершения программы.
     * Программа завершает свою работу с кодом 0 (успешное завершение).
     */
    @Override
    public void execute() {
        System.exit(0);
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}