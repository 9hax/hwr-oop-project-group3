package hwr.oop;

public interface TextUI {
    Game createGame();

    void playRound();

    void playGame();

    boolean askRestart();

    void showHighScores();
}
