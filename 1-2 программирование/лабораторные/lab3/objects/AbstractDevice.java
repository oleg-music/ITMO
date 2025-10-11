package programming.lab3_2.objects;

import java.util.Objects;

abstract class AbstractDevice {
    private String name;

    public AbstractDevice(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        else if (object == null || getClass() != object.getClass())
            return false;
        AbstractDevice abstractDevice = (AbstractDevice) object;
        return name == abstractDevice.name;
    }
}
