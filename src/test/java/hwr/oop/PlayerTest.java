package hwr.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {

    private final RoundState[] states = new RoundState[]{RoundState.STRIKE, RoundState.SPARE, RoundState.NORMAL, RoundState.NORMAL, RoundState.NORMAL
            ,RoundState.NORMAL, RoundState.NORMAL, RoundState.NORMAL, RoundState.NORMAL, RoundState.NORMAL};
    private final int[] roundPoints = new int[]{10, 10,3,5,0,0,0,0,0,0};
    private final int totalPoints = 44;
    private final Player player = new Player("Detlef", 0);

    @Test
    void calculatePointsTest(){
        player.points = roundPoints;
        player.roundStates = states;
        int testTotalPoints = player.calculatePoints(player);
        assertThat(testTotalPoints).isEqualTo(totalPoints);
    }
}
