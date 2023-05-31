package hwr.oop;

import java.util.ArrayList;
import java.util.List;

interface Round {

    int getPoints();


    boolean isStrike();

    boolean isSpare();

    int getBonusPoints();

    void setPreviousRound(Round round);

    void calculateBonusPoints();
    void calculateBonusPoints(List<BowlingThrow> throwList);

    void prepareBonusCounter();

    ArrayList<Round> convertToList();

    void addThrow(BowlingThrow aThrow);

    Round getPreviousRound();

    int getTotalPoints();

    int getRound();

    int getBonusPointCalculationCounter();
}
