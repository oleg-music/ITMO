package programming.lab3_2.objects;

import programming.lab3_2.records.CharacteristicsOfShorties;

public class Znayka extends Shorty{
    private WeightlessnessDevice device = new WeightlessnessDevice("Антилунитовый прибор невесомости");
    private CharacteristicsOfShorties characteristics = new CharacteristicsOfShorties("Знайка",
            246.2f, 0.4f, 10, 121);

    public Znayka(String name) {
        super(name);
    }

    public boolean tryTurnOnWeightlessness(Cave cave) {
        if (this.device.tryTurnOn(this.characteristics.IQ())) {
            System.out.println(this.getName() + " успешно включил " + this.device.getName());
            cave.turnOnWeightlessness();
            return true;
        }
        else {
            System.out.println(this.device.getName() + " заискрил и задымился, эксперимент не удался");
            return false;
        }
    }
}
