package org.example.xml;

import org.example.Main;
import org.example.collectionClasses.Person;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashSet;

/**
 * Класс DOMWriter предоставляет функциональность для записи данных в XML-документы
 * с использованием DOM (Document Object Model).
 * Этот класс позволяет создавать XML-документы из объектов Java и сохранять их в файлы.
 */
public class DOMWriter {
    /**
     * Записывает коллекцию объектов {@link Person} в XML-файл.
     * Каждый объект {@link Person} представляется как элемент {@code <person>} в XML-документе.
     * В случае ошибок ввода-вывода или проблем с созданием XML-документа, выбрасывается исключение.
     *
     * @param personSet Коллекция объектов {@link Person}, которая будет записана в XML-файл.
     */
    public static void writeCollectionInXML(HashSet<Person> personSet) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("persons");
            doc.appendChild(rootElement);

            for (Person person : personSet) {
                Element personElement = doc.createElement("person");

                addElement(doc, personElement, "id", person.getId().toString());
                addElement(doc, personElement, "name", person.getName());

                Element coordinatesElement = doc.createElement("coordinates");
                addElement(doc, coordinatesElement, "x", person.getCoordinates().getX().toString());
                addElement(doc, coordinatesElement, "y", person.getCoordinates().getY().toString());
                personElement.appendChild(coordinatesElement);

                addElement(doc, personElement, "creationDate", person.getCreationDate().toString());

                if (person.getHeight() != null) {
                    addElement(doc, personElement, "height", person.getHeight().toString());
                } else {
                    addElement(doc, personElement, "height", "");
                }

                addElement(doc, personElement, "birthday", person.getBirthday().toString());

                if (person.getEyeColor() != null) {
                    addElement(doc, personElement, "eyeColor", person.getEyeColor().toString());
                } else {
                    addElement(doc, personElement, "eyeColor", "");
                }

                addElement(doc, personElement, "hairColor", person.getHairColor().toString());

                if (person.getLocation() != null) {
                    Element locationElement = doc.createElement("location");
                    addElement(doc, locationElement, "x", Float.toString(person.getLocation().getX()));
                    addElement(doc, locationElement, "y", person.getLocation().getY().toString());
                    addElement(doc, locationElement, "z", person.getLocation().getZ().toString());
                    addElement(doc, locationElement, "name", person.getLocation().getName());
                    personElement.appendChild(locationElement);
                }

                rootElement.appendChild(personElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Main.getFileName()));

            transformer.transform(source, result);

            System.out.println("Коллекция успешно сохранена в файл: " + Main.getFileName());

        } catch (Exception e) {
            System.out.println("Ошибка при сохранении коллекции в файл: " + e.getMessage());
        }
    }

    /**
     * Вспомогательный метод для добавления элемента в XML-документ.
     *
     * @param doc          XML-документ.
     * @param parentElement Родительский элемент, к которому добавляется новый элемент.
     * @param tagName      Название тега.
     * @param textContent  Текстовое содержимое элемента.
     */
    private static void addElement(Document doc, Element parentElement, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        parentElement.appendChild(element);
    }
}
