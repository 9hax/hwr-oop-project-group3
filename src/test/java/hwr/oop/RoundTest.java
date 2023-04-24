package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class RoundTest {
    private final int roundNumber = 1;
    private  final int points = 5;
    private  final Round round = new Round(roundNumber, points);
    private final String pinsHit = "3 5 6";
    private final BowlingStates strikeState = BowlingStates.STRIKE;
    private  final List<Boolean> pinsH =List.of(true,true,false,true,false,false,true,true,true,true);

    @Test
    void newRoundTest() {
        Round testRound = new Round(roundNumber, points);
        Assertions.assertThat(testRound.getRoundNumber()).isEqualTo(round.getRoundNumber());
        Assertions.assertThat(testRound.getRoundPoints()).isEqualTo(round.getRoundPoints());
    }

    @Test
    void convertArrayTest() {
        List<Boolean> pins = round.convertArray(pinsHit);
        Assertions.assertThat(pins).containsExactlyInAnyOrderElementsOf(pinsH);
    }
    @Test
    void countThrowPointsTest() {
        Integer p = round.countThrowPoints(pinsHit);
        Assertions.assertThat(p).isEqualTo(3);
    }
    @Test
    void setStateTest() {
        int th = 1;
        BowlingStates state = round.setState(th, 10);
        Assertions.assertThat(state).isEqualTo(strikeState);
    }
}
