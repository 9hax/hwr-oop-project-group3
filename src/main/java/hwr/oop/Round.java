package hwr.oop;

import java.util.List;

public class Round {
    int counter;
    List<Throw> throwList;
    RoundState state;

    public Round(int counter, List<Throw> throwList) {
        this.counter = counter;
        this.throwList = throwList;
    }

    public int getPoints(){
        //TODO logic SPARE STRIKE
        int roundPoints =0;
        for (Throw singleThrow :
                throwList) {
            roundPoints+=singleThrow.fallenPins;
        }
        return roundPoints;
    }
}
