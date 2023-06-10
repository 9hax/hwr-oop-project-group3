package hwr.oop;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BowlingThrow {
    private String fallenPinsString;
    private int fallenPins;
    public BowlingThrow(String fallenPinsString) {
        setFallenPins(fallenPinsString);
    }
    public BowlingThrow(Integer fallenPins) {
        setFallenPins(fallenPins);
    }

    public BowlingThrow() {
    }

    public void setFallenPins(String fallenPinsString) throws IllegalArgumentException{
        if(fallenPinsString.equals("-")){
            this.fallenPinsString = fallenPinsString;
        }
        if (convertAndSortList(fallenPinsString).get(0)<=10 && convertAndSortList(fallenPinsString).get(0) >= 0) {
            this.fallenPinsString = fallenPinsString;
        } else {
            throw new IllegalArgumentException("FALSCH! Please enter a number between 0 and 10.");
        }
    }
    public void setFallenPins(Integer fallenPins) throws IllegalArgumentException{
        if (fallenPins <= 10 && fallenPins >= 0) {
            this.fallenPins = fallenPins;
        } else {
            throw new IllegalArgumentException("FALSCH! Please enter a number between 0 and 10.");
        }
    }

    public int getFallenPins() {
        if (fallenPinsString == null){
            return fallenPins;
        }
        else{
            return convertAndSortList(fallenPinsString).size();
        }
    }
    public String getFallenPinsString() {
        return fallenPinsString;
    }
    public boolean hasClearedString() {
        return Objects.equals(fallenPins, "1 2 3 4 5 6 7 8 9 10");
    }
    public boolean hasCleared() {
        return fallenPins==10;
    }

    private static List<Integer> convertAndSortList(String scan){
        if(scan.equals("-")){
            return List.of(0);
        }
        return Arrays.stream(scan.split(" ")).map(Integer::parseInt).sorted().collect(Collectors.toList());
    }
}
