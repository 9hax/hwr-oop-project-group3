package hwr.oop;

import java.util.Arrays;

public class NormalRound implements Round {
    private Boolean[] pinsState;

    public NormalRound(){
        pinsState = new Boolean[10];
        Arrays.fill(pinsState, Boolean.TRUE);
    }

    public void play(){
    }

}
