package programming.lab3_2.objects;

import java.util.ArrayList;

import programming.lab3_2.enums.PositionsInSpace;
import programming.lab3_2.interfaces.AbilityToHaveWeightlessness;

public class Cave extends AbstractPlace implements AbilityToHaveWeightlessness {
    private boolean isThereWeightlessness = false;

    public Cave(String name) {
        super(name);
    }

    @Override
    public void turnOnWeightlessness() {
        this.isThereWeightlessness = true;
        ArrayList<Shorty> listOfVisitors = this.getListOfVisitors();
        for (int i = 0; i < listOfVisitors.size(); i++) {
            if ((listOfVisitors.get(i).getPositionInSpace() == PositionsInSpace.ON_THE_GROUND) & (!listOfVisitors.get(i).getHaveAntilunit())) {
                System.out.print(listOfVisitors.get(i).getName() + " отделился от " +
                        getPositionInCave(listOfVisitors.get(i)));
                listOfVisitors.get(i).setPositionInSpace(PositionsInSpace.IN_THE_AIR);
                System.out.print(" и поднялся " + getPositionInCave(listOfVisitors.get(i)) + ", ");
            }
            else if (listOfVisitors.get(i).getPositionInSpace() == PositionsInSpace.ON_THE_GROUND) {
                System.out.print(listOfVisitors.get(i).getName() + " не отделился от земли, так как у него был антилунит ");
            }
        }
    }

    @Override
    public void turnOffWeightlessness() {
        this.isThereWeightlessness = false;
        ArrayList<Shorty> listOfVisitors = this.getListOfVisitors();
        for (int i = 0; i < listOfVisitors.size(); i++) {
            if (listOfVisitors.get(i).getPositionInSpace() == PositionsInSpace.IN_THE_AIR) {
                System.out.print(listOfVisitors.get(i).getName() + " отделился от " +
                        getPositionInCave(listOfVisitors.get(i)));
                listOfVisitors.get(i).setPositionInSpace(PositionsInSpace.ON_THE_GROUND);
                System.out.print(" и опустился на " + getPositionInCave(listOfVisitors.get(i)) + ", ");
            }
        }
    }

    public String getPositionInCave(Shorty shorty) {
        if (shorty.getPositionInSpace() == PositionsInSpace.IN_THE_AIR) {
            return "под потолок пещеры";
        } else {
            return "дно пещеры";
        }
    }
}
