package hwr.oop;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameTest {

    @Test
    void initializeGameTest() {
        Game game = new Game(List.of("Bob", "Alice", "st-Eve"));

        List<Player> ingamePlayers = game.getPlayers();
        List<String> testPlayers = List.of("Bob", "Alice", "st-Eve");
        for (int i = 0; i<3; i++) {
            assertThat(ingamePlayers.get(i).getName()).isEqualTo(testPlayers.get(i));
        }
    }

    /* Command + N: Generate Test Method */

    @Test
    void createGame_getRound() {
        Game game = new Game(List.of("Bruh", "Bro", "Bre"));
        assertThat(game.getRound()).isEqualTo(0);
        Player currentPlayer = game.getCurrentPlayer();
        assertThat(currentPlayer.getName()).isEqualTo("Bruh");
        System.out.println(currentPlayer.getName());
        assertThat(currentPlayer.getRound()).isEqualTo(-1);
        currentPlayer.throwBall(1);
        assertThat(currentPlayer.getPlayerPoints()).isEqualTo(0);
        currentPlayer.throwBall(1);
        assertThat(currentPlayer.getPlayerPoints()).isEqualTo(2);
        // Bruh has two points after the round, but is not the game.CurrentPlayer anymore.

        assertThat(game.getCurrentPlayer().getPlayerPoints()).isEqualTo(0);

        // Next Player
        System.out.println(game.getCurrentPlayer().getName());
        game.getCurrentPlayer().throwBall(1);
        assertThat(game.getCurrentPlayer().getPlayerPoints()).isEqualTo(0);
        game.getCurrentPlayer().throwBall(1);
        assertThat(game.getCurrentPlayer().getPlayerPoints()).isEqualTo(2);
    }
}
