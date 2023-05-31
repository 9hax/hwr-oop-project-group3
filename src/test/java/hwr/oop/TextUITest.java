package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TextUITest {
    @Test
    void startUIPutPlayerName_getGame() {
        IOAdapter ioAdapter = new MockIOAdapter();
        TextUI ui = new ConsoleTextUI(ioAdapter);
        ioAdapter.queueInput("Steve");
        Game game = ui.createGame();
        List <Player> players = game.getPlayers();
        assertThat(players.get(0).getName()).isEqualTo("Steve");
    }
    @Test
    void startUIPutMultiPlayerGame_getGame() {
        IOAdapter ioAdapter = new MockIOAdapter();
        TextUI ui = new ConsoleTextUI(ioAdapter);
        ioAdapter.queueInput("Steve");
        ioAdapter.queueInput("Notch");
        Game game = ui.createGame();
        List <Player> players = game.getPlayers();
        assertThat(players.get(0).getName()).isEqualTo("Steve");
        assertThat(players.get(1).getName()).isEqualTo("Notch");
    }

    @Test
    void startGamePlayRound_roundEquals1() {
        IOAdapter ioAdapter = new MockIOAdapter();
        TextUI ui = new ConsoleTextUI(ioAdapter);
        ioAdapter.queueInput("Alex");
        //ioAdapter.queueInput("Steve");
        Game game = ui.createGame();
        ui.playRound();
        assertThat(game.getRound()).isEqualTo(1);
    }
}
