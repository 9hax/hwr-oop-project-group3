package hwr.oop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        ioAdapter.trimOutputQueue(5);
        assertThat(ioAdapter.pollOutput()).isEqualTo("Sbeve scored 40 points.");
        ioAdapter.ignoreOutputs(2);
        assertThat(ioAdapter.pollOutput()).isEqualTo("Input Y to play another game. \n>");
        assertThat(ioAdapter.pollOutput()).isEqualTo("Goodbye!");
    }

    @Disabled("This will not work since the application expects input and therefore the test will run indefinetely.")
    @Test
    void testMain() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        BowlingApplication.main(null);

        String[] outputLines = outputStream.toString().split(System.lineSeparator());
        String firstOutput = outputLines[0];
        assertThat(firstOutput).isEqualTo("======== HIGHSCORES ========");
    }
}
