package hwr.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalRound implements Round{

    private List<Throw> throwList;
    private Round previousRound;

    int bonusPoints;

    int bonusPointCalculationCounter;

    boolean bonusPointCalculationCounterIsValid;


    public NormalRound() {
        this.throwList = Arrays.asList(new Throw(), new Throw());
        this.previousRound = null;
        this.bonusPointCalculationCounterIsValid = false;
    }

    public NormalRound(List<Throw> throwList) {
        this.previousRound = null;
        this.bonusPointCalculationCounterIsValid = false;

        if (validateThrowList(throwList)) {
            this.throwList = throwList;
            this.prepareBonusCounter();
        } else {
            throw new IllegalArgumentException("FALSCH! This throwList is invalid because more than 10 Pins were hit, which is not a possible scenario.");
        }
    }

    public NormalRound(List<Throw> throwList, Round previousRound) {
        this.previousRound = null;
        this.bonusPointCalculationCounterIsValid = false;

        if (validateThrowList(throwList)) {
            this.throwList = throwList;
            this.previousRound = previousRound;
            this.prepareBonusCounter();
        } else {
            throw new IllegalArgumentException("FALSCH! This throwList is invalid because more than 10 Pins were hit, which is not a possible scenario.");
        }
    }

    @Override
    public int getPoints(){
        int roundPoints =0;
        for (Throw singleThrow :
                throwList) {
            roundPoints+=singleThrow.getFallenPins();
        }
        return roundPoints;
    }


    @Override
    public boolean isStrike() {
        Throw firstThrow = this.throwList.get(0);
        return firstThrow.hasCleared();
    }

    @Override
    public boolean isSpare() {
        if (throwList.size()<2) {
            return false;
        }
        Throw firstThrow = this.throwList.get(0);
        Throw secondThrow = this.throwList.get(1);

        int sum = firstThrow.getFallenPins() + secondThrow.getFallenPins();
        return sum == 10;
    }

    @Override
    public int getBonusPoints() {
        return bonusPoints;
    }

    @Override
    public void setPreviousRound(Round round) {
        this.previousRound = round;
    }

    @Override
    public void calculateBonusPoints() {
        if (previousRound == null){
            return;
        }
        previousRound.calculateBonusPoints(throwList);
    }

    @Override
    public void calculateBonusPoints(List<Throw> throwList){
        List<Throw> tempThrowList = new ArrayList<>(throwList);
        for (; bonusPointCalculationCounter > 0; bonusPointCalculationCounter--){
            if (tempThrowList.isEmpty()) {break;}
            bonusPoints += tempThrowList.get(0).getFallenPins();
            tempThrowList.remove(0);
        }
        List<Throw> fullThrowList = new ArrayList<>(this.throwList);
        fullThrowList.addAll(throwList);
        if (previousRound == null){
            return;
        }
        previousRound.calculateBonusPoints(fullThrowList);
    }

    @Override
    public void prepareBonusCounter(){
        if (bonusPointCalculationCounterIsValid) return;
        if (isStrike()){
            bonusPointCalculationCounter = 2;
        }
        if (isSpare()){
            bonusPointCalculationCounter = 1;
        }
        bonusPointCalculationCounterIsValid = true;
    }

    private static boolean validateThrowList(List<Throw> throwListValidationTarget) {
        int fallenPinsValidityCounter = 0;
        for (Throw singleThrow :
                throwListValidationTarget) {
            fallenPinsValidityCounter += singleThrow.getFallenPins();
        }
        return fallenPinsValidityCounter >= 0 && fallenPinsValidityCounter <= 10;
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
    public void addThrow(Throw aThrow) {
        ArrayList<Throw> newThrowList = new ArrayList<> (this.throwList);
        newThrowList.add(aThrow);
        if (validateThrowList(newThrowList)){
            this.throwList = newThrowList;
        } else {
            throw new IllegalArgumentException("FALSCH! This throwList is invalid because more than 10 Pins were hit, which is not a possible scenario.");
        }
    }
}
