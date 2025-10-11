package org.example.Utils;

/**
 * Класс HistoryManager управляет историей выполненных команд.
 * Хранит последние выполненные команды в массиве фиксированной длины.
 */
public class HistoryManager {

    /**
     * Максимальное количество команд, хранимых в истории.
     */
    private static final int historyLengthList = 9;

    /**
     * Массив для хранения последних выполненных команд.
     */
    private static String[] historyList = new String[historyLengthList];

    /**
     * Добавляет команду в историю.
     * Если история заполнена, самая старая команда удаляется.
     *
     * @param command Команда для добавления в историю.
     */
    public static void addCommand(String command) {
        for (int i = (historyLengthList - 1); i > 0; i--) {
            historyList[i] = historyList[i - 1];
        }
        historyList[0] = command;
    }

    /**
     * Возвращает массив с историей выполненных команд.
     *
     * @return Массив строк, содержащий последние выполненные команды.
     */
    public static String[] getHistoryList() {
        return historyList;
    }
}