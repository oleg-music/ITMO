package org.example.collectionClasses;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Класс Person представляет объект, содержащий информацию о человеке.
 * Включает такие данные, как имя, координаты, рост, дата рождения, цвет глаз, цвет волос, местоположение, id, дата создания.
 * Реализует интерфейс Comparable для сортировки объектов по росту.
 */
public class Person implements Comparable<Person> {

    /**
     * Уникальный идентификатор объекта. Не может быть null, значение должно быть больше 0.
     * Генерируется автоматически.
     */
    private Integer id;

    /**
     * Имя человека. Не может быть null или пустой строкой.
     */
    private String name;

    /**
     * Координаты человека. Не могут быть null.
     */
    private Coordinates coordinates;

    /**
     * Дата создания объекта. Не может быть null. Генерируется автоматически.
     */
    private java.util.Date creationDate;

    /**
     * Рост человека. Может быть null, значение должно быть больше 0.
     */
    private Long height;

    /**
     * Дата рождения человека. Не может быть null.
     */
    private java.time.ZonedDateTime birthday;

    /**
     * Цвет глаз человека. Может быть null.
     */
    private EyeColor eyeColor;

    /**
     * Цвет волос человека. Не может быть null.
     */
    private HairColor hairColor;

    /**
     * Местоположение человека. Может быть null.
     */
    private Location location;

    /**
     * Счетчик для автоматической генерации уникального идентификатора.
     */
    private static Integer idCounter = 1;

    /**
     * Конструктор класса Person.
     *
     * @param name        Имя человека. Не может быть null или пустой строкой.
     * @param coordinates Координаты человека. Не могут быть null.
     * @param height      Рост человека. Может быть null, значение должно быть больше 0.
     * @param birthday    Дата рождения человека. Не может быть null.
     * @param eyeColor    Цвет глаз человека. Может быть null.
     * @param hairColor   Цвет волос человека. Не может быть null.
     * @param location    Местоположение человека. Может быть null.
     */
    public Person(String name, Coordinates coordinates, Long height, java.time.ZonedDateTime birthday,
                  EyeColor eyeColor, HairColor hairColor, Location location) {
        this.name = name;
        this.coordinates = coordinates;
        this.height = height;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.location = location;

        this.id = idCounter;
        idCounter += 1;
        this.creationDate = new Date();
    }

    /**
     * Возвращает идентификатор объекта.
     *
     * @return Идентификатор.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Возвращает дату создания объекта.
     *
     * @return Дата создания.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает местоположение человека.
     *
     * @return Местоположение.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Возвращает цвет волос человека.
     *
     * @return Цвет волос.
     */
    public HairColor getHairColor() {
        return hairColor;
    }

    /**
     * Возвращает цвет глаз человека.
     *
     * @return Цвет глаз.
     */
    public EyeColor getEyeColor() {
        return eyeColor;
    }

    /**
     * Возвращает дату рождения человека.
     *
     * @return Дата рождения.
     */
    public ZonedDateTime getBirthday() {
        return birthday;
    }

    /**
     * Возвращает рост человека.
     *
     * @return Рост.
     */
    public Long getHeight() {
        return height;
    }

    /**
     * Возвращает координаты человека.
     *
     * @return Координаты.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Возвращает имя человека.
     *
     * @return Имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает дату создания для человека.
     *
     * @param creationDate Дату создания для человека. Поле не может быть null, Значение этого поля должно
     *                     генерироваться автоматически
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Устанавливает Id человека.
     *
     * @param id Id человека. Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно
     *           быть уникальным, Значение этого поля должно генерироваться автоматически
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public static void setIdCounter(Integer idCounter) {
        Person.idCounter = idCounter;
    }

    /**
     * Устанавливает имя человека.
     *
     * @param name Имя человека. Не может быть null или пустой строкой.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Устанавливает координаты человека.
     *
     * @param coordinates Координаты человека. Не могут быть null.
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Устанавливает рост человека.
     *
     * @param height Рост человека. Может быть null, значение должно быть больше 0.
     */
    public void setHeight(Long height) {
        this.height = height;
    }

    /**
     * Устанавливает дату рождения человека.
     *
     * @param birthday Дата рождения человека. Не может быть null.
     */
    public void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }

    /**
     * Устанавливает цвет глаз человека.
     *
     * @param eyeColor Цвет глаз человека. Может быть null.
     */
    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Устанавливает цвет волос человека.
     *
     * @param hairColor Цвет волос человека. Не может быть null.
     */
    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * Устанавливает местоположение человека.
     *
     * @param location Местоположение человека. Может быть null.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Сравнивает объекты Person по росту.
     *
     * @param other Объект Person для сравнения.
     * @return Результат сравнения (отрицательное число, если текущий объект меньше,
     * положительное число, если больше, и 0, если объекты равны).
     */
    @Override
    public int compareTo(Person other) {
        if (this.height == null && other.height == null) {
            return 0;
        } else if (this.height == null) {
            return -1;
        } else if (other.height == null) {
            return 1;
        } else {
            return this.height.compareTo(other.height);
        }
    }

    /**
     * Возвращает строковое представление объекта Person.
     *
     * @return Строковое представление объекта в формате:
     * "Person{id=..., name='...', coordinates=..., eyeColor=..., creationDate=...,
     * birthday=..., height=..., hairColor=..., location=...}".
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", eyeColor=" + eyeColor +
                ", creationDate=" + creationDate +
                ", birthday=" + birthday +
                ", height=" + height +
                ", hairColor=" + hairColor +
                ", location=" + location +
                '}';
    }
}