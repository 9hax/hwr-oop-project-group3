package hwr.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalRound implements Round{

    private List<BowlingThrow> throwList;
    private NormalRound previousRound;

    int bonusPoints;

    int bonusPointCalculationCounter;

    boolean bonusPointCalculationCounterIsValid;

    static final String MORE_THAN_10_PINS_ERROR = "A loaded throwList was invalid.";

    public NormalRound() {
        this.throwList = Arrays.asList(new BowlingThrow(), new BowlingThrow());
        this.previousRound = null;
        this.bonusPointCalculationCounterIsValid = false;
    }

    public NormalRound(List<BowlingThrow> throwList) {
        this.previousRound = null;
        this.bonusPointCalculationCounterIsValid = false;

        if (validateThrowList(throwList)) {
            this.throwList = throwList;
            this.prepareBonusCounter();
        } else {
            throw new IllegalArgumentException(MORE_THAN_10_PINS_ERROR);
        }
    }

    public NormalRound(List<BowlingThrow> throwList, Round previousRound) {
        this.previousRound = null;
        this.bonusPointCalculationCounterIsValid = false;

        if (validateThrowList(throwList)) {
            this.throwList = throwList;
            this.previousRound = (NormalRound) previousRound;
            this.prepareBonusCounter();
        } else {
            throw new IllegalArgumentException(MORE_THAN_10_PINS_ERROR);
        }
    }

    @Override
    public int getPoints(){
        int roundPoints =0;
        for (BowlingThrow singleThrow :
                throwList) {
            roundPoints+=singleThrow.getFallenPins();
        }
        return roundPoints+getBonusPoints();
    }


    @Override
    public boolean isStrike() {
        BowlingThrow firstThrow = this.throwList.get(0);
        return firstThrow.hasCleared();
    }

    @Override
    public boolean isSpare() {
        if (throwList.size()<2) {
            return false;
        }
        BowlingThrow firstThrow = this.throwList.get(0);
        BowlingThrow secondThrow = this.throwList.get(1);

        int sum = firstThrow.getFallenPins() + secondThrow.getFallenPins();
        return sum == 10;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    @Override
    public void setPreviousRound(Round round) {
        this.previousRound = (NormalRound) round;
    }


    public void calculateBonusPoints() {
        if (previousRound == null){
            return;
        }
        previousRound.calculateBonusPoints(throwList);
    }

    public void calculateBonusPoints(List<BowlingThrow> throwList){
        List<BowlingThrow> tempThrowList = new ArrayList<>(throwList);
        for (; bonusPointCalculationCounter > 0; bonusPointCalculationCounter--){
            if (tempThrowList.isEmpty()) {break;}
            bonusPoints += tempThrowList.get(0).getFallenPins();
            tempThrowList.remove(0);
        }
        List<BowlingThrow> fullThrowList = new ArrayList<>(this.throwList);
        fullThrowList.addAll(throwList);
        if (previousRound == null){
            return;
        }
        previousRound.calculateBonusPoints(fullThrowList);
    }

    public void prepareBonusCounter(){
        if (bonusPointCalculationCounterIsValid) return;
        if (isStrike()){
            bonusPointCalculationCounter = 2;
            bonusPointCalculationCounterIsValid = true;
        }
        if (isSpare()){
            bonusPointCalculationCounter = 1;
            bonusPointCalculationCounterIsValid = true;
        }
    }


    public ArrayList<Round> convertToList(){
        ArrayList<Round> tempRoundList;
        if(previousRound != null) {
            tempRoundList = previousRound.convertToList();
        } else {
            tempRoundList = new ArrayList<>();
        }
        tempRoundList.add(this);
        return tempRoundList;
    }

    @Override
    public void addThrow(BowlingThrow aThrow) {
        ArrayList<BowlingThrow> newThrowList = new ArrayList<> (this.throwList);
        newThrowList.add(aThrow);
        if (validateThrowList(newThrowList)){
            this.throwList = newThrowList;
        } else {
            throw new IllegalArgumentException(MORE_THAN_10_PINS_ERROR);
        }
    }

    @Override
    public Round getPreviousRound() {
        return previousRound;
    }

    @Override
    public int getTotalPoints(){
        if(previousRound != null) {
            calculateBonusPoints();
            int totalPoints = previousRound.getTotalPoints();
            return totalPoints+getPoints();
        } else {
            return getPoints();
        }
    }
    @Override
    public int getRound() {
        if(previousRound == null) {
            return 0;
        } else {
            return previousRound.getRound() + 1;
        }
    }

    public int getBonusPointCalculationCounter() {
        return bonusPointCalculationCounter;
    }

    private static boolean validateThrowList(List<BowlingThrow> throwListValidationTarget) {
        int fallenPinsValidityCounter = 0;
        String fallenPinsValidityString = "";

        for (BowlingThrow singleThrow :
                throwListValidationTarget) {
            if(singleThrow.getFallenPinsString() == null){
                fallenPinsValidityCounter += singleThrow.getFallenPins();
            }
            else{
                if(fallenPinsValidityString.contains(singleThrow.getFallenPinsString())){
                    return false;
                }
                fallenPinsValidityString=fallenPinsValidityString.concat(singleThrow.getFallenPinsString());
            }
        }
        return fallenPinsValidityCounter <= 10;
    }
}
