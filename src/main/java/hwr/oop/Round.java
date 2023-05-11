package hwr.oop;

interface Round {

    int getPoints();


    boolean isStrike();

    boolean isSpare();

    int getBonusPoints();

    void setPreviousRound(Round round);

    void calculateBonusPoints();
}
