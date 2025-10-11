package org.example.Utils;

import java.util.HashSet;

public class ScriptManager {
    /**
     * Множество, хранящее имена выполняемых скриптов для предотвращения рекурсии.
     */
    private static final HashSet<String> executingScripts = new HashSet<>();

    /**
     * Проверяет, выполняется ли в данный момент скрипт с указанным именем файла.
     *
     * @param fileName Имя файла скрипта.
     * @return true, если скрипт выполняется, иначе false.
     */
    public static boolean isScriptExecuting(String fileName) {
        return executingScripts.contains(fileName);
    }

    /**
     * Добавляет имя файла скрипта в множество выполняемых скриптов.
     *
     * @param fileName Имя файла скрипта.
     */
    public static void addExecutingScript(String fileName) {
        executingScripts.add(fileName);
    }

    /**
     * Удаляет имя файла скрипта из множества выполняемых скриптов.
     *
     * @param fileName Имя файла скрипта.
     */
    public static void removeExecutingScript(String fileName) {
        executingScripts.remove(fileName);
    }

    /**
     * Проверяет, пусто ли множество выполняемых скриптов.
     *
     * @return true, если множество пусто, иначе false.
     */
    public static boolean isExecutingScriptsEmpty() {
        return executingScripts.isEmpty();
    }
}