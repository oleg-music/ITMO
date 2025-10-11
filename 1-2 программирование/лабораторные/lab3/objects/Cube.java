package programming.lab3_2.objects;

import programming.lab3_2.records.CharacteristicsOfShorties;

public class Cube extends Shorty {
    private CharacteristicsOfShorties characteristics = new CharacteristicsOfShorties("Кубик",
            241.0f, 0.6f, 10, 105);

    public Cube(String name) {
        super(name);
    }
}
