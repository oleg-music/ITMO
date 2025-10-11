package programming.lab3_2.interfaces;

import programming.lab3_2.enums.PositionsInSpace;

public interface Positionable {
    void setPositionInSpace(PositionsInSpace positionInSpace);

    PositionsInSpace getPositionInSpace();
}
