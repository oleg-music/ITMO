package org.example;

import org.example.Utils.HistoryManager;
import org.example.commandsPackage.*;

import java.util.HashMap;

/**
 * Класс Invoker отвечает за вызов и выполнение команд, введенных пользователем.
 * Он хранит доступные команды в HashMap и выполняет их на основе введенных данных.
 * Также поддерживает управление выполняемыми скриптами и историей команд.
 */
public class Invoker {
    /**
     * HashMap, хранящий команды. Ключом является название команды, значением — объект команды.
     */
    private static final HashMap<String, Command> commands = new HashMap<>();

    /**
     * Аргумент, передаваемый команде.
     */
    private static String argument = "";

    static {
        commands.put("help", new HelpCommand(commands));
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("add", new AddCommand());
        commands.put("update", new UpdateCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCommand());
        commands.put("exit", new ExitCommand());
        commands.put("count_greater_than_birthday", new CountGreaterThanBirthdayCommand());
        commands.put("history", new HistoryCommand());
        commands.put("print_unique_eye_color", new PrintUniqueEyeColorCommand());
        commands.put("print_field_descending_birthday", new PrintFieldDescendingBirthdayCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("remove_lower", new RemoveLowerCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
    }

    /**
     * Выполняет команду на основе введенной строки.
     * Разделяет строку на команду и аргумент, затем вызывает соответствующий метод execute().
     * Если команда неизвестна, выбрасывает исключение NullPointerException.
     *
     * @param line Введенная пользователем строка, содержащая команду и опционально аргумент.
     * @throws NullPointerException Если команда не найдена в HashMap commands.
     */
    public void invoke(String line) throws NullPointerException {
        String[] splitedLine = line.split(" ");
        Command command = commands.get(splitedLine[0].trim());
        if (splitedLine.length > 1) {
            argument = splitedLine[1];
        }
        try {
            command.execute();
            HistoryManager.addCommand(splitedLine[0]);
        } catch (NullPointerException e) {
            System.out.println("Неизвестная команда");
        }
    }

    /**
     * Возвращает текущий аргумент, переданный команде.
     *
     * @return Аргумент команды.
     */
    public static String getArgument() {
        return argument;
    }
}