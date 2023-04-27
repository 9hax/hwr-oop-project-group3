package hwr.oop;

public interface Round {
    int play();
    default void updatePinState(boolean[] hitPins) {
        boolean[] pinsState = new boolean[10];
        for(int i = 0; i < pinsState.length-1; i++) {
            if (hitPins[i]) {
                pinsState[i] = false;
            }
        }
    }
    default int getHitPinCount(boolean[] pinsState) {
        int hitPinCount = 0;
        for (boolean pin :
                pinsState) {
            if (!pin) hitPinCount++;
        }
        return hitPinCount;
    }

}