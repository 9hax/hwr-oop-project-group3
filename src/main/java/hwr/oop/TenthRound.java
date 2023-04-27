package hwr.oop;

import java.util.Arrays;

public class TenthRound implements Round {

    private boolean[] pinsState;
    private RoundState roundState;

    public TenthRound(){
        pinsState = new boolean[10];
        Arrays.fill(pinsState, Boolean.TRUE);
    }

    public int play(){
        boolean[] hitPins = SinglePlay.convertHitPins(UserInterface.parseInputNumberList(UserInterface.promptUser("Last round!\nWhich pins have been hit?\n>")));
        updatePinState(hitPins);
        int points;

        if (SinglePlay.allHit(pinsState)) {
            points = 10;
            roundState = RoundState.STRIKE;
            UserInterface.promptUser("You hit a strike.You get two additional throws.");
            for(int th = 0; th<2; th++){
                hitPins = SinglePlay.convertHitPins(UserInterface.parseInputNumberList(UserInterface.promptUser
                        (String.format("Throw number %d",(th+1) ))));
                updatePinState(hitPins);
                points += getHitPinCount(pinsState);
            }
            return points;
        }

        hitPins = SinglePlay.convertHitPins(UserInterface.parseInputNumberList(UserInterface.promptUser("Which pins have been hit?\n>")));
        updatePinState(hitPins);

        if (SinglePlay.allHit(pinsState)) {
            points = 10;
            roundState = RoundState.SPARE;
            UserInterface.promptUser("You hit a spare.You get one additional throw.");
            hitPins = SinglePlay.convertHitPins(UserInterface.parseInputNumberList(UserInterface.promptUser
                    (String.format("Throw number 1"))));
            updatePinState(hitPins);
            points += getHitPinCount(pinsState);
            return points;
        }
        points = getHitPinCount(pinsState);
        return points;
    }
}
