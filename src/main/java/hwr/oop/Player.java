package hwr.oop;

import java.util.List;

public class Player {


    private final String name;
    private Round lastFinishedRound;
    private Round tempRound;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public boolean throwBall(int fallenPins) {
        if (tempRound != null){
            tempRound.addThrow(new Throw(fallenPins));
            updateLastPlayedRound();
            return  false;
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

    public Round getTempRound() {
        return tempRound;
    }

    public Round getLastPlayedRound() {
        return lastFinishedRound;
    }
}
