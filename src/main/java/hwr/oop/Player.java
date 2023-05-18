package hwr.oop;

import java.util.List;

public class Player{

    int gamePoints = 0;

    private final String name;
    private Round lastFinishedRound;
    private Round tempRound;

    Player(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    boolean throwBall(int fallenPins) {
        if (tempRound != null){
            tempRound.addThrow(new Throw(fallenPins));
            updateLastPlayedRound();
            return false;
        } else {
            tempRound = new NormalRound(List.of(new Throw(fallenPins)));
            if (tempRound.isStrike()){
                updateLastPlayedRound();
                return false;
            } else {
                return true;
            }
        }
    }

    private void updateLastPlayedRound(){
        tempRound.setPreviousRound(lastFinishedRound);
        lastFinishedRound = tempRound;
        tempRound = null;
    }

    Round getTempRound() {
        return tempRound;
    }

     Round getLastPlayedRound() {
        return lastFinishedRound;
    }

    int calculatePlayerGamePoints(){
        gamePoints += getLastPlayedRound().getPoints();
        return gamePoints;
    }
    //TODO make everything package-private
}