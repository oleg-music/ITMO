package programming.lab3_2.objects;

import programming.lab3_2.interfaces.Visitable;

import java.util.ArrayList;
import java.util.Objects;


abstract class AbstractPlace implements Visitable {
    private String name;
    private ArrayList<Shorty> visitors;

    public AbstractPlace(String name) {
        this.name = name;
        ArrayList<Shorty> visitors = new ArrayList<>();
        this.visitors = visitors;
    }

    @Override
    public void appendVisitor(Shorty visitor) {
        this.visitors.add(visitor);
    }

    @Override
    public ArrayList getListOfVisitors() {
        return this.visitors;
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
        AbstractPlace abstractPlace = (AbstractPlace) object;
        return name == abstractPlace.name;
    }
}
