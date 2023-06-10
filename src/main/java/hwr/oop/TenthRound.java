package hwr.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TenthRound implements Round {

    private List<BowlingThrow> throwList;
    private TenthRound previousRound;

    static final String MORE_PINS_THAN_POSSIBLE = "FALSCH! More pins got hit than possible";

    public TenthRound() {
        this.throwList = Arrays.asList(new BowlingThrow(), new BowlingThrow());
        this.previousRound = null;
    }

    public TenthRound(List<BowlingThrow> throwList) {
        this.previousRound = null;
        boolean validation = validateThrowList(throwList);

        if (validation) {
            this.throwList = throwList;
        } else {
            throw new IllegalArgumentException(MORE_PINS_THAN_POSSIBLE);
        }
    }

    public TenthRound(List<BowlingThrow> throwList, Round previousRound) {
        this.previousRound = null;
        boolean validation = validateThrowList(throwList);
        if (validation) {
            this.throwList = throwList;
            this.previousRound = (TenthRound) previousRound;
        } else {
            throw new IllegalArgumentException(MORE_PINS_THAN_POSSIBLE);
        }
    }

    @Override
    public int getPoints() {
        int roundPoints = 0;
        for (BowlingThrow singleThrow :
                throwList) {
            roundPoints += singleThrow.getFallenPins();
        }
        return roundPoints;
    }

    public int getExtraThrows() {
        int extraThrows = 0;
        if (isStrike()) {
            extraThrows = 2;
        }
        if (isSpare()) {
            extraThrows = 1;
        }
        return extraThrows;
    }

    @Override
    public boolean isStrike() {
        BowlingThrow firstThrow = this.throwList.get(0);
        return firstThrow.hasCleared();
    }

    @Override
    public boolean isSpare() {
        if (throwList.size() < 2) {
            return false;
        }
        BowlingThrow firstThrow = this.throwList.get(0);
        BowlingThrow secondThrow = this.throwList.get(1);

        int sum = firstThrow.getFallenPins() + secondThrow.getFallenPins();
        return sum == 10;
    }

    @Override
    public void setPreviousRound(Round round) {
        this.previousRound = (TenthRound) round;
    }

    public ArrayList<Round> convertToList() {
        ArrayList<Round> tempRoundList;
        if (previousRound != null) {
            tempRoundList = previousRound.convertToList();
        } else {
            tempRoundList = new ArrayList<>();
        }
        tempRoundList.add(this);
        return tempRoundList;
    }

    @Override
    public void addThrow(BowlingThrow aThrow) {
        ArrayList<BowlingThrow> newThrowList = new ArrayList<>(this.throwList);
        newThrowList.add(aThrow);

        if (validateThrowList(throwList)) {
            this.throwList = newThrowList;
        } else {
            throw new IllegalArgumentException(MORE_PINS_THAN_POSSIBLE);
        }
    }


    @Override
    public Round getPreviousRound() {
        return previousRound;
    }

    @Override
    public int getTotalPoints() {
        if (previousRound != null) {
            int totalPoints = previousRound.getTotalPoints();
            return totalPoints + getPoints();
        }
        return getPoints();
    }

    @Override
    public int getRound() {
        return 10;
    }


    private static boolean validateThrowList(List<BowlingThrow> throwListValidationTarget) {
        int fallenPinsValidityCounter = 0;
        for (BowlingThrow singleThrow :
                throwListValidationTarget) {
            fallenPinsValidityCounter += singleThrow.getFallenPins();
        }
        boolean validity = fallenPinsValidityCounter >= 0 && fallenPinsValidityCounter <= 10;
        if(throwListValidationTarget.get(0).getFallenPins() == 10 ){
            validity = fallenPinsValidityCounter >= 10 && fallenPinsValidityCounter <= 29;
        }
        return validity;
    }
}