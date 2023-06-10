package hwr.oop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HighScoreTest {

    @AfterEach
    void deleteJsonFile(){
        deleteFile("defaultPersistence.json");
        deleteFile("emptyHighScoreFile.json");
        deleteFile("highscores.json");
        deleteFile("testHighscores.json");
    }

    void deleteFile(String path){
        Path fileToDeletePath = Paths.get(path);
        try {
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            System.out.println("Tried to delete test file at "+path+", but test file was nonexistent");
        }
    }

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
        hsh.saveScore(new ScorePrimitive("Karl - 01.01.1001, 01:01 UTC", 6));
        assertThat(hsh.getHighScores().getScorePrimitiveList().get(0).getName()).isEqualTo("Karsten - 01.01.1001, 01:01 UTC");
        assertThat(hsh.getHighScores().getScorePrimitiveList().get(3).getName()).isEqualTo("Karlos - 01.01.1001, 01:01 UTC");
    }

    @Test
    void saveHighscore_ExecuteFunctionTest() {
        IOAdapter mockIO = new MockIOAdapter();
        HighscoreHandler hsh = new HighscoreHandler(mockIO, "testHighscores");
        hsh.clearHighscores();
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

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void emptyFile_Test(){
        try {
            Path fileToDeletePath = Paths.get("emptyHighScoreFile.json");
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            System.out.println("Tried to delete test file, but test file was nonexistent");
        }
        try {
            new File("emptyHighScoreFile.json").createNewFile();
        } catch (IOException e) {
            System.out.println("Tried to touch test file, but test file was existent");
        }
        IOAdapter mockIO = new MockIOAdapter();
        HighscoreHandler hsh = new HighscoreHandler(mockIO, "emptyHighScoreFile");
        assertThat(hsh.getHighScores().getScorePrimitiveList().isEmpty()).isTrue();
    }
}
