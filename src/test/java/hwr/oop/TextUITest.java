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
        assertThat(ioAdapter.pollOutput()).isEqualTo("Please input the names of each player. Please press ENTER after each name. Press ENTER twice if you have written down all the player names. \n>");
        assertThat(ioAdapter.pollOutput()).isEqualTo("Registered players: Steve");
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
    void startGamePlayRound_gameIsInFirstRound() {
        IOAdapter ioAdapter = new MockIOAdapter();
        TextUI ui = new ConsoleTextUI(ioAdapter);
        ioAdapter.queueInput("Alex");
        ioAdapter.queueInput("");
        ioAdapter.queueInput("2");
        ioAdapter.queueInput("4");
        //ioAdapter.queueInput("Steve");
        Game game = ui.createGame();
        ui.playRound();
        ioAdapter.pollOutput();
        ioAdapter.pollOutput();
        assertThat(ioAdapter.pollOutput()).isEqualTo("Current round is #0");
        assertThat(ioAdapter.pollOutput()).isEqualTo("How many pins did Alex hit? >");
        assertThat(ioAdapter.pollOutput()).isEqualTo("Alex hit 2 pins and throws again.");
        assertThat(ioAdapter.pollOutput()).isEqualTo("How many pins did Alex hit? >");
        assertThat(ioAdapter.pollOutput()).isEqualTo("Alex hit 4 pins and has finished this round.");
        assertThat(game.getRound()).isZero();
    }

    @Test
    void scorePoints_printScores() {
        IOAdapter ioAdapter = new MockIOAdapter();
        TextUI ui = new ConsoleTextUI(ioAdapter);
        ioAdapter.queueInput("Alex");
        ioAdapter.queueInput("");
        ioAdapter.queueInput("2");
        ioAdapter.queueInput("A");
        ui.createGame();
        ui.playRound();
        assertThat(ioAdapter.lastOutput()).isEqualTo("Alex has scored 2 points.");
    }

    @Test
    void playGameTest() {
        IOAdapter ioAdapter = new MockIOAdapter();
        TextUI ui = new ConsoleTextUI(ioAdapter);
        ioAdapter.queueInput("Alex");
        ioAdapter.queueInput("Steve von der Steve");
        ioAdapter.queueInput("");

        ioAdapter.queueInput("3");
        ioAdapter.pollOutput();
        for(int round = 0; round<= 39; round++){
            ioAdapter.queueInput("2");
        }
        ui.createGame();
        ui.playGame();
        ioAdapter.pollOutput();
        ioAdapter.pollOutput();

        assertThat(ioAdapter.pollOutput()).isEqualTo("The game starts now!");

        for(int round = 0; round<= 39; round++){
            ioAdapter.pollOutput();
        }
        System.out.println(ioAdapter.pollOutput());
        assertThat(ioAdapter.lastOutput()).isEqualTo("The winner is Alex with 41 points. CongratulaZZ1ONES!");
    }

}

