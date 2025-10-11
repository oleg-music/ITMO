package programming.lab3_2.objects;

import programming.lab3_2.records.CharacteristicsOfShorties;

public class Tube extends Shorty {
    private CharacteristicsOfShorties characteristics = new CharacteristicsOfShorties("Тюбик",
            232.9f, 0.3f, 11, 74);

    public Tube(String name) {
        super(name);
    }
}
