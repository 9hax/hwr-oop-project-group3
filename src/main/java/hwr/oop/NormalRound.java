package hwr.oop;

import java.util.Arrays;

public class NormalRound implements Round {
    private boolean[] pinsState;


    private RoundState roundState;

    public NormalRound(){
        pinsState = new boolean[10];
        Arrays.fill(pinsState, Boolean.TRUE);
    }

    public int play(){
        UserInterface ui = new ConsoleUI(new ConsoleIOHandler(false));
        boolean[] hitPins = SinglePlay.convertHitPins(ui.parseInputNumberList(ui.promptUser("Which pins have been hit?\n>")));
        updatePinState(hitPins);

        if (SinglePlay.allHit(pinsState)) {
            roundState = RoundState.STRIKE;
            return 10;
        }

        hitPins = SinglePlay.convertHitPins(ui.parseInputNumberList(ui.promptUser("Which pins have been hit?\n>")));
        updatePinState(hitPins);

        if (SinglePlay.allHit(pinsState)) {
            roundState = RoundState.SPARE;
            return 10;
        }
        int pinCount = getHitPinCount(pinsState);
        return 10 - pinCount;
    }


}
