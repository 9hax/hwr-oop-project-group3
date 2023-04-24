package hwr.oop;

import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
public class Round {

    private Integer roundNumber;
    private BowlingStates state;
    public Integer roundPoints;
    private final int pinsTotal = 10;
    private BowlingStates initial = BowlingStates.NORMAL;
    private Game game = new Game();

    public Round(Integer roundNumber, Integer roundPoints) {
        this.roundNumber = roundNumber;
        this.roundPoints = roundPoints;
    }

    BowlingStates playedState;
    public static Player pl;
    static final Scanner gameScanner = new Scanner(System.in);
    public static List<Integer> extraRounds = new ArrayList<>();

    /** **
     ** We calculate the points which were gained by the amount of hitted pins.
     **  @param points string of hit pins provided by the player
     **  @return the amount of hit pins.
     **/
    public int countThrowPoints(String points){
        return Collections.frequency(convertArray(points), false);
    }

    /** **
     ** We have an array of pins. They are currently all standing (true). If they get hit, the will fall (false)
     * If the provided input is equal to "-" the user didn't hit any pins at all. The function checks if the
     * input is valid, otherwise it asks to repeat the input. The pins which got hit will get the value
     * false. In the end the pins get "set up" again and we return to our initial array.
     **  @param scan string of hit pins provided by the player
     **  @return the hit pins.
     **/
    List<Boolean> convertArray(String scan){
        List<Boolean> pins = Arrays.asList(true, true, true, true, true, true, true, true, true, true);
        List<String> pinsHit = List.of(scan.split(" "));
        if(scan.equals("-")){
            return pins;
        }
        for (String pin : pinsHit) {
            int hitPin = Integer.parseInt(pin);
            if (hitPin < 0 || hitPin > 10) {
                System.out.println("Type a valid number.");
                break;
            }
            if (pins.get(hitPin - 1).equals(false)) {
                System.out.println("Pin already got hit. Type a valid pin.");
                break;
            }
            pins.set(hitPin - 1, false);
        }
        return pins;
    }
    /** **
     ** We set the state of a round. if the 10 pins got hit with the first throw, it's a strike. If 10 pins
     * got hit with the second throw, it is a spare, otherwise it is a normal throw after which nothing happens.
     * After strikes or spares further actions can follow.
     **  @param th- the current throw and points which were made with the current throw
     **  @return The state of the throw.
     **/
    BowlingStates setState(int th, int points) {
        BowlingStates state;
        if(points == pinsTotal && th==1){
            state = BowlingStates.STRIKE;
        }else if(points == pinsTotal && th==2){
            state = BowlingStates.SPARE;
        }else{
            state = BowlingStates.NORMAL;
        }
        return state;
    }
}



