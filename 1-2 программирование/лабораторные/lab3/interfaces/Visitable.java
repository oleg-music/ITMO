package programming.lab3_2.interfaces;

import programming.lab3_2.objects.Shorty;

import java.util.ArrayList;

public interface Visitable {
    void appendVisitor(Shorty visitor);

    ArrayList getListOfVisitors();
}
