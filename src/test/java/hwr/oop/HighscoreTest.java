package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        assertThat(hsh.getHighscores().getScorePrimitiveList().get(0).getName()).isEqualTo("Karsten - 01.01.1001, 01:01 UTC");
        assertThat(hsh.getHighscores().getScorePrimitiveList().get(3).getName()).isEqualTo("Karbellos - 01.01.1001, 01:01 UTC");
        assertThat(hsh.getHighscores().getScorePrimitiveList().get(1).getName()).isEqualTo("Karstachstan - 01.01.1001, 01:01 UTC");
    }
}
