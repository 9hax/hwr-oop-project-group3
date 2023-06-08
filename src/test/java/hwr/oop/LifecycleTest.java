package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
class LifecycleTest {
    @Test
    void testLifecycle() {
        IOAdapter ioAdapter = new MockIOAdapter();

        HighscoreHandler hsh = new HighscoreHandler(ioAdapter, "highscores");
        hsh.clearHighscores();
        hsh.saveScore(new ScorePrimitive("Konrad - 01.01.1001, 01:01 UTC", 7));

        ioAdapter.queueInput("Alex");
        ioAdapter.queueInput("");

        for(int round = 0; round< 20; round++){
            ioAdapter.queueInput("3");
        }
        ioAdapter.queueInput(""); // Don't save the game
        ioAdapter.queueInput("Y"); // Play another game! YYAYY!!!! slay~

        ioAdapter.queueInput("Sbeve");
        ioAdapter.queueInput("");

        for(int round = 0; round< 20; round++){
            ioAdapter.queueInput("2");
        }
        ioAdapter.queueInput(""); // Don't save the game
        ioAdapter.queueInput("N"); // Don't play another game.

        BowlingApplication.appLifecycle(ioAdapter);

        assertThat(ioAdapter.pollOutput()).isEqualTo("======== HIGHSCORES ========");
        assertThat(ioAdapter.pollOutput()).isEqualTo("1. Place: Konrad - 01.01.1001, 01:01 UTC with 7 points");
        ioAdapter.trimOutputQueue(4);
        assertThat(ioAdapter.pollOutput()).isEqualTo("Sbeve scored 40 points.");
        ioAdapter.ignoreOutputs(1);
        assertThat(ioAdapter.pollOutput()).isEqualTo("Input Y to play another game. \n>");
        assertThat(ioAdapter.pollOutput()).isEqualTo("Goodbye!");
    }
}
