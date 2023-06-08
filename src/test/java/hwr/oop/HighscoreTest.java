package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HighscoreTest {
    @Test
    void saveHighscore_loadHighscore() {
        IOAdapter mockIO = new MockIOAdapter();
        HighscoreHandler hsh = new HighscoreHandler(mockIO, "testHighscores");
        hsh.clearHighscores();
        hsh.saveScore(new ScorePrimitive("Karsten - 01.01.1001, 01:01 UTC", 7));
        hsh.saveScore(new ScorePrimitive("Karstachstan - 01.01.1001, 01:01 UTC", 6));
        hsh.saveScore(new ScorePrimitive("Karlos - 01.01.1001, 01:01 UTC", 5));
        hsh.saveScore(new ScorePrimitive("Karbeldeutschland - 01.01.1001, 01:01 UTC", 3));
        hsh.saveScore(new ScorePrimitive("Karbellos - 01.01.1001, 01:01 UTC", 4));
        assertThat(hsh.getHighScores().getScorePrimitiveList().get(0).getName()).isEqualTo("Karsten - 01.01.1001, 01:01 UTC");
        assertThat(hsh.getHighScores().getScorePrimitiveList().get(3).getName()).isEqualTo("Karbellos - 01.01.1001, 01:01 UTC");
        assertThat(hsh.getHighScores().getScorePrimitiveList().get(1).getName()).isEqualTo("Karstachstan - 01.01.1001, 01:01 UTC");
    }

    @Test
    void saveHighscore_ExecuteFunctionTest() {
        IOAdapter mockIO = new MockIOAdapter();
        HighscoreHandler hsh = new HighscoreHandler(mockIO, "testHighscores");
        hsh.saveScore(new ScorePrimitive("Karsten - 01.01.1001, 01:01 UTC", 7));

        HighscoreHandler hsh2 = new HighscoreHandler(mockIO, "testHighscores");
        assertThat(hsh2.getHighScores().getScorePrimitiveList().get(0).getName()).isEqualTo("Karsten - 01.01.1001, 01:01 UTC");
    }

    @Test
    void clearHighScore_Test() {
        IOAdapter mockIO = new MockIOAdapter();
        HighscoreHandler hsh = new HighscoreHandler(mockIO, "testHighscores");
        hsh.saveScore(new ScorePrimitive("Karsten - 01.01.1001, 01:01 UTC", 7));
        hsh.clearHighscores();
        assertThat(hsh.getHighScores().getScorePrimitiveList().isEmpty()).isTrue();
        HighscoreHandler hsh2 = new HighscoreHandler(mockIO, "testHighscores");
        assertThat(hsh2.getHighScores().getScorePrimitiveList().isEmpty()).isTrue();
        assertDoesNotThrow(()->hsh.saveScore(new ScorePrimitive("Karlos - 01.01.1001, 01:01 UTC", 5)));
    }


}
