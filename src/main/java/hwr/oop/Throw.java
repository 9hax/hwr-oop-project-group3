package hwr.oop;

public class Throw {
    int fallenPins;
    public void setFallenPins(int fallenPins) throws IllegalArgumentException{
        if (fallenPins <= 10 && fallenPins >= 0) {
            this.fallenPins = fallenPins;
        } else {
            throw new IllegalArgumentException("FALSCH! Please enter a number between 0 and 10.");
        }
    }

    public int getFallenPins() {
        return fallenPins;
    }
}
