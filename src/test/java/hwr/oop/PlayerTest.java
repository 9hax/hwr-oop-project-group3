package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    @Test
    void createPlayerWithName_getPlayerName() {
        Player namedPlayer = new Player("Steve");
        assertThat(namedPlayer.getName()).isEqualTo("Steve");
    }

    @Test
    void playerPlaysNormalRound_getGameStatus() {
        Player namedPlayer = new Player("Steve");
        boolean continueRound = namedPlayer.throwBall(5);
        assertThat(continueRound).isTrue();
        continueRound = namedPlayer.throwBall(3);
        assertThat(continueRound).isFalse();
    }
}
