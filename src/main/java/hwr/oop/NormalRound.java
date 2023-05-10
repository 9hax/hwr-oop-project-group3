package hwr.oop;

import java.util.Arrays;
import java.util.List;

public class NormalRound implements Round{
    int counter;
    List<Throw> throwList;
    RoundState state;

    public NormalRound() {
        this.throwList = Arrays.asList(new Throw(), new Throw());
    }

    public NormalRound(List<Throw> throwList) {
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
