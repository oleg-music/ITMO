package programming.lab3_2.objects;

import programming.lab3_2.enums.*;
import programming.lab3_2.exceptions.IsNotDoingSmthException;
import programming.lab3_2.interfaces.*;

public class Shorty extends AbstractCreature implements Positionable, Poseable,
        ActionsInWeightlessness, Suitable, AbilityToHaveAntilunit {
    private PositionsInSpace positionInSpace = PositionsInSpace.ON_THE_GROUND;
    private PosesInAir poseInAir = PosesInAir.ABSENT;
    private PosesOnTheGround poseOnTheGround = PosesOnTheGround.STANDING;
    private Actions nowAction = Actions.DOES_NOTHING;
    private Suits suit = Suits.CASUAL_SUIT;
    private AbstractPlace ap;
    private Boolean haveAntilunit = false;

    public Shorty(String name) {
        super(name);
    }

    @Override
    public void setPositionInSpace(PositionsInSpace positionInSpace) {
        this.positionInSpace = positionInSpace;
        if (positionInSpace == PositionsInSpace.ON_THE_GROUND) {
            this.poseInAir = PosesInAir.ABSENT;
            this.poseOnTheGround = PosesOnTheGround.STANDING;
        } else {
            this.poseOnTheGround = PosesOnTheGround.ABSENT;
            this.poseInAir = PosesInAir.STARFISH_POSE;
        }
    }

    @Override
    public void setPoseInAir(PosesInAir poseInAir) {
        if (this.positionInSpace == PositionsInSpace.IN_THE_AIR) {
            this.poseInAir = poseInAir;
        }
    }

    @Override
    public void setPoseOnTheGround(PosesOnTheGround poseOnTheGround) {
        if (this.positionInSpace == PositionsInSpace.ON_THE_GROUND) {
            this.poseOnTheGround = poseOnTheGround;
        }
    }

    @Override
    public PositionsInSpace getPositionInSpace() {
        return this.positionInSpace;
    }

    @Override
    public String getPoseInAir() {
        if (this.poseInAir == PosesInAir.STARFISH_POSE) {
            return "в позе морской звезды";
        } else if (this.poseInAir == PosesInAir.UPSIDE_DOWN) {
            return "в позе вверх тормашками";
        } else {
            return "без позы";
        }
    }

    @Override
    public String getPoseOnTheGround() {
        if (this.poseOnTheGround == PosesOnTheGround.SITTING) {
            return "в позе сидя";
        } else if (this.poseOnTheGround == PosesOnTheGround.LYING) {
            return "в позе лёжа";
        } else if (this.poseOnTheGround == PosesOnTheGround.STANDING) {
            return "в позе стоя";
        } else {
            return "без позы";
        }
    }

    @Override
    public void swimInWeightlessness() {
        if (this.positionInSpace == PositionsInSpace.IN_THE_AIR) {
            this.nowAction = Actions.SWIMMING;
        }
    }

    @Override
    public void tryGoDown() {
        if (this.positionInSpace == PositionsInSpace.IN_THE_AIR) {
            if (Math.random() < 0.15) {
                this.positionInSpace = PositionsInSpace.ON_THE_GROUND;
                this.poseInAir = PosesInAir.ABSENT;
                this.poseOnTheGround = PosesOnTheGround.STANDING;
                this.nowAction = Actions.DOES_NOTHING;
                System.out.println(this.getName() + " смог спуститься вниз");
            } else {
                System.out.println(this.getName() + " старался спуститься вниз, но не смог");
            }
        }
    }

    @Override
    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    @Override
    public String getSuit() {
        if (this.suit == Suits.SPACE_SUIT) {
            return "Находясь в скафандре";
        } else {
            return "Находясь в повседневном костюме";
        }
    }

    @Override
    public void stopDoingSomething() throws IsNotDoingSmthException {
        if (this.nowAction != Actions.DOES_NOTHING) {
            this.nowAction = Actions.DOES_NOTHING;
        }
        else {
            throw new IsNotDoingSmthException(this.getName());
        }
    }

    @Override
    public String getNowAction() {
        if (this.nowAction == Actions.SWIMMING) {
            return "плавает в воздухе";
        } else {
            return "ничего не делает";
        }
    }

    @Override
    public void setHaveAntilunit(boolean haveAntilunit) {
        this.haveAntilunit = haveAntilunit;
    }

    @Override
    public boolean getHaveAntilunit() {
        return this.haveAntilunit;
    }

    public String abilityToUseReactiveForces() {
        if (this.suit == Suits.SPACE_SUIT) {
            return "не мог использовать реактивные силы";
        } else {
            return "мог использовать реактивные силы";
        }
    }

    public String abilityToCalculateBodyMovements() {
        if (this.suit == Suits.SPACE_SUIT) {
            return "не мог рассчитать свои телодвижения";
        } else {
            return "мог рассчитать свои телодвижения";
        }
    }
}
