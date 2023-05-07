package hwr.oop;

import java.util.Arrays;

public class NormalRound implements Round {
    private boolean[] pinsState;

    public int getHitPinCount() {
        int hitPinCount = 0;
        for (boolean pin :
                pinsState) {
            if (!pin) hitPinCount++;
        }
        return hitPinCount;
    }

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

        int pinCount = 0;
        for (boolean pin :
                pinsState) {
            if (pin) pinCount++;
        }
        return 10 - pinCount;

    }

    private void updatePinState(boolean[] hitPins) {
        for(int i = 0; i < this.pinsState.length-1; i++) {
            if (hitPins[i]) {
                this.pinsState[i] = false;
            }
        }
    }

}
