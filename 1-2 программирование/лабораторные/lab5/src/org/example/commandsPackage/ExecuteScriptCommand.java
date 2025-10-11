package org.example.commandsPackage;

import org.example.Invoker;
import org.example.Utils.Reader;
import org.example.Utils.ScriptManager;

import java.util.ArrayList;

/**
 * Класс ExecuteScriptCommand реализует команду выполнения скрипта из указанного файла.
 * Скрипт содержит команды, которые выполняются последовательно.
 * Команда предотвращает рекурсивное выполнение скриптов.
 */
public class ExecuteScriptCommand implements Command {
    private static ArrayList<String> remainingScriptStrings;

    /**
     * Выполняет команду выполнения скрипта из указанного файла.
     * Если скрипт уже выполняется, выводит сообщение о рекурсии и завершает выполнение.
     * Иначе считывает команды из файла и выполняет их последовательно.
     */
    @Override
    public void execute() {
        String fileName = Invoker.getArgument();

        if (ScriptManager.isScriptExecuting(fileName)) {
            System.out.println("Обнаружена рекурсия: скрипт " + fileName + " уже выполняется.");
            return;
        }

        ScriptManager.addExecutingScript(fileName);

        ArrayList<String> scriptStrings = Reader.readFileInStrings(fileName);

        Invoker invoker = new Invoker();

        while (!scriptStrings.isEmpty()) {
            String command = scriptStrings.get(0).trim();
            scriptStrings.remove(0);
            remainingScriptStrings = scriptStrings;
            invoker.invoke(command);
        }
        ScriptManager.removeExecutingScript(fileName);
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла." +
                " В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    public static ArrayList<String> getRemainingScriptStrings() {
        return remainingScriptStrings;
    }

    public static void setRemainingScriptStrings(ArrayList<String> remainingScriptStrings) {
        ExecuteScriptCommand.remainingScriptStrings = remainingScriptStrings;
    }
}