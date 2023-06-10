package hwr.oop;

import java.util.ArrayList;
import java.util.List;

interface Round {

    int getPoints();

    boolean isStrike();

    boolean isSpare();


    void setPreviousRound(Round round);

    ArrayList<Round> convertToList();

    void addThrow(BowlingThrow aThrow);

    Round getPreviousRound();

    int getTotalPoints();

    int getRound();
}
