package programming.lab3_2;
import programming.lab3_2.enums.PosesInAir;
import programming.lab3_2.enums.Suits;
import programming.lab3_2.exceptions.IsNotDoingSmthException;
import programming.lab3_2.objects.*;

public class Main {
    public static void main(String[] args) throws IsNotDoingSmthException{
        Tube tube = new Tube("Тюбик");
        Gusli gusli = new Gusli("Гусля");
        Cube cube = new Cube("Кубик");
        Znayka znayka = new Znayka("Знайка");

        tube.setSuit(Suits.SPACE_SUIT);
        cube.setSuit(Suits.SPACE_SUIT);
        gusli.setSuit(Suits.SPACE_SUIT);
        znayka.setSuit(Suits.SPACE_SUIT);

        Cave cave = new Cave("Пещера");

        cave.appendVisitor(tube);
        cave.appendVisitor(gusli);
        cave.appendVisitor(cube);
        cave.appendVisitor(znayka);
        znayka.setHaveAntilunit(true);

        if (znayka.tryTurnOnWeightlessness(cave)) {

            tube.swimInWeightlessness();
            cube.swimInWeightlessness();
            gusli.swimInWeightlessness();

            cube.setPoseInAir(PosesInAir.UPSIDE_DOWN);

            System.out.print(znayka.getName() + " " + znayka.getNowAction() + " " + cave.getPositionInCave(znayka) + " " +
                    znayka.getPoseInAir());
            System.out.print("\n" + tube.getName() + " " + tube.getNowAction() + " " + cave.getPositionInCave(tube) + " " +
                    tube.getPoseInAir() + ", ");
            System.out.print(cube.getName() + " " + cube.getNowAction() + " " + cave.getPositionInCave(cube) + " " +
                    cube.getPoseInAir() + ", ");
            System.out.print(gusli.getName() + " " + gusli.getNowAction() + " " + cave.getPositionInCave(gusli) + " " +
                    gusli.getPoseInAir() + "\n");

            tube.tryGoDown();
            cube.tryGoDown();
            gusli.tryGoDown();

            System.out.println(tube.getSuit() + " " + tube.getName() + " " + tube.abilityToCalculateBodyMovements() +
                    " и " + tube.abilityToUseReactiveForces());
            System.out.println(cube.getSuit() + " " + cube.getName() + " " + cube.abilityToCalculateBodyMovements() +
                    " и " + cube.abilityToUseReactiveForces());
            System.out.println(gusli.getSuit() + " " + gusli.getName() + " " + gusli.abilityToCalculateBodyMovements() +
                    " и " + gusli.abilityToUseReactiveForces());

            try {
                znayka.stopDoingSomething();
            }
            catch (IsNotDoingSmthException newException) {
                System.out.println(newException.getMessage());
            }
        }
    }
}
