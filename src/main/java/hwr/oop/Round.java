package hwr.oop;

import java.util.List;

interface Round {

    int getPoints();


    boolean isStrike();

    boolean isSpare();

    int getBonusPoints();

    void setPreviousRound(Round round);

    void calculateBonusPoints();
    void calculateBonusPoints(List<Throw> throwList);

    void prepareBonusCounter();
}
