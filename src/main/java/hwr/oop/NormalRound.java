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

        if (validateThrowList(throwList)) {
            this.throwList = throwList;
        } else {
            throw new IllegalArgumentException("FALSCH! This throwList is invalid because more than 10 Pins were hit, which is not a possible scenario.");
        }
    }


    public int getPoints(){
        //TODO logic SPARE STRIKE
        int roundPoints =0;
        for (Throw singleThrow :
                throwList) {
            roundPoints+=singleThrow.getFallenPins();
        }
        return roundPoints;
    }

    @Override
    public RoundState getState() {
        return state;
    }

    @Override
    public boolean isStrike() {
        Throw firstThrow = this.throwList.get(0);
        return firstThrow.hasCleared();
    }

    private static boolean validateThrowList(List<Throw> throwListValidationTarget) {
        int fallenPinsValidityCounter = 0;
        for (Throw singleThrow :
                throwListValidationTarget) {
            fallenPinsValidityCounter += singleThrow.getFallenPins();
        }
        return fallenPinsValidityCounter >= 0 && fallenPinsValidityCounter <= 10;
    }


}
