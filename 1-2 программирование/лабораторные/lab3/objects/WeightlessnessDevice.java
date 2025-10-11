package programming.lab3_2.objects;

public class WeightlessnessDevice extends AbstractDevice {

    public WeightlessnessDevice(String name) {
        super(name);
    }

    public boolean tryTurnOn(Integer IQ) {
        if (IQ > 100 & (Math.random() < 0.75)) {
            return true;
        }
        else {
            return false;
        }
    }
}
