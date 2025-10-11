package programming.lab3_2.objects;

import programming.lab3_2.exceptions.IsNotDoingSmthException;

import java.util.Objects;

abstract class AbstractCreature {
    private String name;

    public AbstractCreature(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    abstract String getNowAction();

    abstract void stopDoingSomething() throws IsNotDoingSmthException;

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
        AbstractCreature abstractCreature = (AbstractCreature) object;
        return name == abstractCreature.name;
    }
}
