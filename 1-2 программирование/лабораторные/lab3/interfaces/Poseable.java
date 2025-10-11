package programming.lab3_2.interfaces;

import programming.lab3_2.enums.PosesInAir;
import programming.lab3_2.enums.PosesOnTheGround;

public interface Poseable {
    void setPoseInAir(PosesInAir poseInAir);

    void setPoseOnTheGround(PosesOnTheGround poseOnTheGround);

    String getPoseInAir();

    String getPoseOnTheGround();

}
