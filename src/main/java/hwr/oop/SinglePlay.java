package hwr.oop;

public class SinglePlay {

    /**
     * Converts a list of pin indices to a boolean array.
     * @param pinIndices Array of integers referring to the hit pins.
     * @return Array of Booleans representing the pins.
     */
    boolean[] convertHitPins(int[] pinIndices) {
        boolean[] booleans = new boolean[10];
        for (int pinIndex: pinIndices ) {
            if (pinIndex <= 10 && pinIndex > 0) {
                booleans[pinIndex - 1] = true;
            }
        }
        return booleans;
    }


}
