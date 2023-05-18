package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameTest {

    @Test
    void initializeGameTest() {
        Game game = new Game();
        assertThat(game).isInstanceOf(Game.class);
    }
}
