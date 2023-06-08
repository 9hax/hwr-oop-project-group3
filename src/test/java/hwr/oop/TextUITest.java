package hwr.oop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TextUITest {
    @AfterEach
    void deleteJsonFile(){
        Path fileToDeletePath = Paths.get("defaultPersistence.json");
        try {
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            System.out.println("Tried to delete test file, but test file was nonexistent");
        }
    }

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
    void startUIPutMultiPlayerGame_getGame_SanitizedNames() {
        IOAdapter ioAdapter = new MockIOAdapter();
        TextUI ui = new ConsoleTextUI(ioAdapter);
        ioAdapter.queueInput("Steve ");
        ioAdapter.queueInput(" Notch");
        ioAdapter.queueInput("Kevins    ");

        Game game = ui.createGame();
        List <Player> players = game.getPlayers();
        assertThat(players.get(0).getName()).isEqualTo("Steve");
        assertThat(players.get(1).getName()).isEqualTo("Notch");
        assertThat(players.get(2).getName()).isEqualTo("Kevins");

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
        ioAdapter.ignoreOutputs(2);
        assertThat(ioAdapter.pollOutput()).isEqualTo("Current round is #1");
        ioAdapter.ignoreOutputs(1);
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


        ioAdapter.queueInput("2");
        ioAdapter.queueInput("8");
        ioAdapter.queueInput("10");
        ioAdapter.queueInput("1234524365");

        ioAdapter.queueInput("2");
        ioAdapter.queueInput("1234524365");
        ioAdapter.queueInput("2");
        for(int round = 0; round<= 34; round++){
            ioAdapter.queueInput("2");
        }
        ui.createGame();
        ui.playGame();
        ioAdapter.ignoreOutputs(2);

        assertThat(ioAdapter.pollOutput()).isEqualTo("The game starts now!");
        ioAdapter.ignoreOutputs(1);
        assertThat(ioAdapter.pollOutput()).isEqualTo("It's Alex's turn.");
        ioAdapter.ignoreOutputs(4);
        assertThat(ioAdapter.pollOutput()).isEqualTo("Alex just scored a SPARE!");
        ioAdapter.ignoreOutputs(5);
        assertThat(ioAdapter.pollOutput()).isEqualTo("Steve von der Steve just scored a STRIKE!");
        ioAdapter.ignoreOutputs(5);
        assertThat(ioAdapter.pollOutput()).isEqualTo("Invalid input! Please try again.");
        assertThat(ioAdapter.pollOutput()).isEqualTo("Current round is #2");
        assertThat(ioAdapter.pollOutput()).isEqualTo("This is the first throw this round.");
        ioAdapter.ignoreOutputs(5);
        assertThat(ioAdapter.pollOutput()).isEqualTo("This is the second throw this round.");


        ioAdapter.trimOutputQueue(6);

        assertThat(ioAdapter.pollOutput()).isEqualTo("Game is finished!");
        assertThat(ioAdapter.pollOutput()).isEqualTo("The winner is Steve von der Steve with 50 points. CongratulaZZ1ONES!");
        assertThat(ioAdapter.pollOutput()).isEqualTo("The Scores are the following:");
        assertThat(ioAdapter.pollOutput()).isEqualTo("Alex scored 48 points.");
        assertThat(ioAdapter.pollOutput()).isEqualTo("Steve von der Steve scored 50 points.");
    }

    @Test
    void restartAfterGameEnd() {
        IOAdapter ioAdapter = new MockIOAdapter();
        TextUI ui = new ConsoleTextUI(ioAdapter);
        ioAdapter.queueInput("Y");
        assertThat(ui.askRestart()).isTrue();
        assertThat(ioAdapter.pollOutput()).isEqualTo("Input Y to play another game. \n>");
        ioAdapter.queueInput("");
        assertThat(ui.askRestart()).isFalse();
        assertThat(ioAdapter.pollOutput()).isEqualTo("Input Y to play another game. \n>");
    }

    @Test
    void saveGameData_Test() {
        IOAdapter ioAdapter = new MockIOAdapter();
        TextUI ui = new ConsoleTextUI(ioAdapter);
        ioAdapter.queueInput("BingoCat");
        ioAdapter.queueInput("");

        for(int ballThrows = 0; ballThrows< 20; ballThrows++){
            ioAdapter.queueInput("2");
        }
        ioAdapter.queueInput("S");
        ioAdapter.queueInput("YoMum");
        ui.playGame();
        assertThat(new JSONPersistence(ioAdapter).load("YoMum").getScorePrimitiveList().get(0).getName()).isEqualTo("BingoCat");
    }
}

