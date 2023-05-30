package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UIAdapterTest {
    @Test
    void startUIPutPlayerNames_getGame() {
        IOAdapter ioAdapter = new MockIOAdapter();
        UI ui = new ConsoleUI(ioAdapter);
        ioAdapter.queueInput("Steve");
        Game game = ui.createGame();
        List <Player> players = game.getPlayers();
        assertThat(players.get(0).getName()).isEqualTo("Steve");
    }
}
