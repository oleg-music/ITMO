package org.example.Utils;

import org.example.collectionClasses.*;
import org.example.commandsPackage.ExecuteScriptCommand;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Класс PersonCreation отвечает за создание объектов Person.
 * Предоставляет методы для создания объектов Person из XML-строки и через консольный ввод.
 */
public class PersonCreation {
    /**
     * Создает объект Person через консольный ввод.
     * Запрашивает у пользователя данные для каждого поля объекта Person.
     *
     * @return Созданный объект Person.
     */
    public static Person createPerson() {
        if (ScriptManager.isExecutingScriptsEmpty()) {
            String name = null; // Поле не может быть null, Строка не может быть пустой
            Coordinates coordinates = null; // Поле не может быть null
            Long height = null; // Поле может быть null, Значение поля должно быть больше 0
            java.time.ZonedDateTime birthday = null; // Поле не может быть null
            EyeColor eyeColor = null; // Поле может быть null
            HairColor hairColor = null; // Поле не может быть null
            Location location = null; // Поле может быть null
            boolean is_correct = false;

            System.out.print("Введите имя: ");

            while (!is_correct) {
                name = Reader.nextLine();
                if (name.trim() != "") {
                    is_correct = true;
                } else {
                    System.out.print("Поле не может быть пустым, введите имя еще раз: ");
                }
            }

            is_correct = false;
            System.out.print("Введите координаты в формате 'x y': ");

            while (!is_correct) {
                String stringCoordinates = Reader.nextLine();
                if (stringCoordinates.trim() != "") {
                    try {
                        String[] enteredCoordinates = stringCoordinates.split(" ");
                        Long x = Long.valueOf(enteredCoordinates[0]);
                        Float y = Float.valueOf(enteredCoordinates[1]);
                        if (y <= -679) {
                            System.out.print("Значение координаты 'y' у поля coordinates должно быть больше -679." +
                                    " Введите координаты еще раз: ");
                        } else {
                            coordinates = new Coordinates(x, y);
                            is_correct = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.print("Неверный формат. Введите координаты в формате 'x y': ");
                    } catch (NumberFormatException e) {
                        System.out.print("Неверный формат. Введите координаты в формате 'x y', где x и y - числа: ");
                    }
                } else {
                    System.out.print("Поле не может быть пустым. Введите координаты в формате 'x y': ");
                }
            }

            is_correct = false;
            System.out.print("Введите рост: ");

            while (!is_correct) {
                String stringHeight = Reader.nextLine();
                try {
                    if (stringHeight == "") {
                        height = null;
                        is_correct = true;
                    } else {
                        height = Long.valueOf(stringHeight);
                        if (height > 0) {
                            is_correct = true;
                        } else {
                            System.out.print("Рост должен быть больше 0, введите рост еще раз: ");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Неверный формат. Введите число: ");
                }
            }

            is_correct = false;
            System.out.print("Введите дату рождения в формате dd.MM.yyyy (например, 01.01.2001): ");

            while (!is_correct) {
                String stringBirthday = Reader.nextLine();
                if (stringBirthday.trim() != "") {
                    try {
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        LocalDate localDate = LocalDate.parse(stringBirthday, dateFormatter);
                        birthday = localDate.atStartOfDay(ZoneId.systemDefault());
                        is_correct = true;
                    } catch (DateTimeParseException e) {
                        System.out.print("Неверный формат. Введите дату рождения в формате dd.MM.yyyy " +
                                "(например, 01.01.2001): ");
                    }
                } else {
                    System.out.print("Поле не может быть пустым. Введите дату рождения еще раз: ");
                }
            }

            is_correct = false;
            System.out.print("Введите цвет глаз, любой из: ");
            for (
                    EyeColor value : EyeColor.values()) {
                System.out.print(value + " ");
            }

            while (!is_correct) {
                String stringEyeColor = Reader.nextLine();
                try {
                    if (stringEyeColor == "") {
                        eyeColor = null;
                        is_correct = true;
                    } else {
                        eyeColor = EyeColor.valueOf(stringEyeColor);
                        is_correct = true;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.print("Такого цвета глаз не существует. Введите цвет глаз еще раз: ");
                }
            }

            is_correct = false;
            System.out.print("Введите цвет волос, любой из: ");
            for (
                    HairColor value : HairColor.values()) {
                System.out.print(value + " ");
            }

            while (!is_correct) {
                String stringHairColor = Reader.nextLine();
                if (stringHairColor.trim() != "") {
                    try {
                        hairColor = HairColor.valueOf(stringHairColor);
                        is_correct = true;
                    } catch (IllegalArgumentException e) {
                        System.out.print("Такого цвета волос не существует. Введите цвет волос еще раз: ");
                    }
                } else {
                    System.out.print("Поле не может быть пустым. Введите цвет волос еще раз: ");
                }
            }

            is_correct = false;
            System.out.print("Введите координаты и название локации в формате 'x y z nameOfLocation': ");

            while (!is_correct) {
                String stringLocation = Reader.nextLine();
                try {
                    if (stringLocation == "") {
                        location = null;
                        is_correct = true;
                    } else {
                        String[] enteredLocation = stringLocation.split(" ");
                        float x = Float.parseFloat(enteredLocation[0]);
                        Integer y = Integer.valueOf(enteredLocation[1]);
                        Float z = Float.valueOf(enteredLocation[2]);
                        String nameOfLocation = enteredLocation[3];
                        location = new Location(x, y, z, nameOfLocation);
                        is_correct = true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.print("Неверный формат. Введите координаты и название локации в формате" +
                            " 'x y z nameOfLocation': ");
                } catch (NumberFormatException e) {
                    System.out.print("Неверный формат. Введите координаты и название локации в формате" +
                            " 'x y z nameOfLocation', где x, y, z - числа, а nameOfLocation - строка: ");
                }
            }
            return new Person(name, coordinates, height, birthday, eyeColor, hairColor, location);
        } else {
            ArrayList<String> remainingScriptStrings = ExecuteScriptCommand.getRemainingScriptStrings();
            if (remainingScriptStrings == null || remainingScriptStrings.size() < 7) {
                System.out.println("Ошибка: Недостаточно данных для создания объекта Person.");
                return null;
            }

            try {
                String name = remainingScriptStrings.get(0);
                String coordinates = remainingScriptStrings.get(1);
                String height = remainingScriptStrings.get(2);
                String birthday = remainingScriptStrings.get(3);
                String eyeColor = remainingScriptStrings.get(4);
                String hairColor = remainingScriptStrings.get(5);
                String location = remainingScriptStrings.get(6);
                remainingScriptStrings.subList(0, 7).clear();
                ExecuteScriptCommand.setRemainingScriptStrings(remainingScriptStrings);

                if (name.trim().isEmpty() || coordinates.trim().isEmpty() || birthday.trim().isEmpty() || hairColor.trim().isEmpty()) {
                    System.out.println("Ошибка: Обязательные поля не могут быть пустыми.");
                    return null;
                }

                String[] coordParts = coordinates.split(" ");
                if (coordParts.length != 2) {
                    System.out.println("Ошибка: Неверный формат координат. Ожидается 'x y'.");
                    return null;
                }
                Long x = Long.valueOf(coordParts[0]);
                Float y = Float.valueOf(coordParts[1]);
                if (y <= -679) {
                    System.out.println("Ошибка: Значение координаты 'y' должно быть больше -679.");
                    return null;
                }
                Coordinates coords = new Coordinates(x, y);

                Long heightValue = null;
                if (!height.trim().isEmpty()) {
                    heightValue = Long.valueOf(height);
                    if (heightValue <= 0) {
                        System.out.println("Ошибка: Рост должен быть больше 0.");
                        return null;
                    }
                }

                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate localDate = LocalDate.parse(birthday, dateFormatter);
                java.time.ZonedDateTime birthdayDate = localDate.atStartOfDay(ZoneId.systemDefault());

                EyeColor eyeColorValue = null;
                if (!eyeColor.trim().isEmpty()) {
                    eyeColorValue = EyeColor.valueOf(eyeColor);
                }

                HairColor hairColorValue = HairColor.valueOf(hairColor);

                String[] locationParts = location.split(" ");
                if (locationParts.length != 4) {
                    System.out.println("Ошибка: Неверный формат локации. Ожидается 'x y z name'.");
                    return null;
                }
                float locX = Float.parseFloat(locationParts[0]);
                Integer locY = Integer.valueOf(locationParts[1]);
                Float locZ = Float.valueOf(locationParts[2]);
                String locName = locationParts[3];
                Location loc = new Location(locX, locY, locZ, locName);

                return new Person(name, coords, heightValue, birthdayDate, eyeColorValue, hairColorValue, loc);

            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Неверный формат числа в данных.");
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: Неверный формат даты. Ожидается формат dd.MM.yyyy.");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: Некорректное значение для enum (цвет глаз или волос).");
            } catch (Exception e) {
                System.out.println("Ошибка при создании объекта Person: " + e.getMessage());
            }

            return null;
        }
    }
}
