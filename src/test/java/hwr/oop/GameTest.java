package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GameTest {

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
    void createGame_checkPlayerOrder() {
        Game game = new Game(List.of("Bruh", "Bro", "Bre"));
        assertThat(game.getRound()).isEqualTo(-1);

        Player currentPlayer = game.getCurrentPlayer();
        assertThat(currentPlayer.getName()).isEqualTo("Bruh");

        assertThat(currentPlayer.getRound()).isEqualTo(-1);
        currentPlayer.throwBallNormal(4);
        assertThat(currentPlayer.getPlayerPoints()).isZero();
        currentPlayer.throwBallNormal(2);
        assertThat(currentPlayer.getPlayerPoints()).isEqualTo(6);

        // Bruh has six points after the round, but is not the game.CurrentPlayer anymore.
        assertThat(currentPlayer).isNotEqualTo(game.getCurrentPlayer());

        currentPlayer = game.getCurrentPlayer();
        assertThat(currentPlayer.getName()).isEqualTo("Bro");

        assertThat(currentPlayer.getPlayerPoints()).isZero();
        currentPlayer.throwBallNormal(3);
        assertThat(currentPlayer.getPlayerPoints()).isZero();
        currentPlayer.throwBallNormal(5);
        assertThat(currentPlayer.getPlayerPoints()).isEqualTo(8);

        // Bro has 8 points after the round, but is not the game.CurrentPlayer anymore.
        assertThat(currentPlayer).isNotEqualTo(game.getCurrentPlayer());

        currentPlayer = game.getCurrentPlayer();
        assertThat(currentPlayer.getName()).isEqualTo("Bre");

        assertThat(currentPlayer.getPlayerPoints()).isZero();
        currentPlayer.throwBallNormal(1);
        assertThat(currentPlayer.getPlayerPoints()).isZero();
        currentPlayer.throwBallNormal(8);
        assertThat(currentPlayer.getPlayerPoints()).isEqualTo(9);

        // Bro has 8 points after the round, but is not the game.CurrentPlayer anymore.
        assertThat(currentPlayer).isNotEqualTo(game.getCurrentPlayer());
        assertThat(game.getCurrentPlayer().getName()).isEqualTo("Bruh");
    }

    @Test
    void test_to_round10() {
        Game game = new Game(List.of("Hänsel", "Gretel"));
        assertThat(game.getCurrentPlayer().getName()).isEqualTo("Hänsel");
        assertThat(game.getRound()).isEqualTo(-1);
        for(int i = 0; i < 40; i++) {
            game.getCurrentPlayer().throwBallNormal(1);
        }
        assertThat(game.getRound()).isEqualTo(9);
    }

    @Test
    void getWinner_with_no_players() {
        Game game = new Game(List.of());
        assertThat(game.determineWinner()).isNull();
    }
}
