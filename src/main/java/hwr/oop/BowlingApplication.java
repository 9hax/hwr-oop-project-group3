package hwr.oop;

public class BowlingApplication {
    public static void main(String[] args) {
        appLifecycle(new ConsoleIOAdapter());
    }

    public static void appLifecycle(IOAdapter ioAdapter) {
        TextUI applicationUI = new ConsoleTextUI(ioAdapter);
        applicationUI.showHighScores();
        boolean playAnotherGame = true;
        while(playAnotherGame) {
            applicationUI.createGame();
            applicationUI.playGame();
            playAnotherGame = applicationUI.askRestart();
        }
        ioAdapter.putString("Goodbye!");
    }
}