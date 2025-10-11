package programming.lab3_2.objects;

import programming.lab3_2.records.CharacteristicsOfShorties;

public class Gusli extends Shorty {
    private CharacteristicsOfShorties characteristics = new CharacteristicsOfShorties("Гусля",
            220.7f, 0.45f, 9, 68);

    public Gusli(String name) {
        super(name);
    }
}