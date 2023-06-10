package hwr.oop;

import java.util.List;

public class Player {
    private final String name;
    private Round lastFinishedRound;
    private Round tempRound;

    Player(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    boolean throwBallNormal(int fallenPins) {
        if (tempRound != null) {
            tempRound.addThrow(new BowlingThrow(fallenPins));
            ((NormalRound) tempRound).prepareBonusCounter();
            updateLastPlayedRound();
            return false;
        } else {
            tempRound = new NormalRound(List.of(new BowlingThrow(fallenPins)));
            if (tempRound.isStrike()) {
                updateLastPlayedRound();
                return false;
            } else {
                return true;
            }
        }
    }
    boolean throwBallTen(int fallenPins) {
        if (tempRound != null) {
            tempRound.addThrow(new BowlingThrow(fallenPins));
            updateLastPlayedRound();
            return false;
        } else {
            tempRound = new TenthRound(List.of(new BowlingThrow(fallenPins)));
            if (tempRound.isStrike()) {
                updateLastPlayedRound();
                return false;
            } else {
                return true;
            }
        }
    }

    private void updateLastPlayedRound() {
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

    public int getPlayerPoints() {

        if (lastFinishedRound != null) {
            return lastFinishedRound.getTotalPoints();
        }
        return 0;
    }

    public int getRound() {
        if (lastFinishedRound != null) {
            return lastFinishedRound.getRound();
        }
        return -1;
    }
}