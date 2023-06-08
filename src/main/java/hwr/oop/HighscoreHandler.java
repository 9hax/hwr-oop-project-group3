package hwr.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighscoreHandler {
    private IOAdapter ioAdapter;
    private String persistenceKey;
    private List<ScorePrimitive> highscores;

    public HighscoreHandler(IOAdapter ioAdapter, String persistenceKey) {
        this.ioAdapter = ioAdapter;
        this.persistenceKey = persistenceKey;
        loadHighscores();
    }

    public void clearHighscores() {
        highscores = new ArrayList<>();
        highscores.clear();
        saveAllScores();
    }

    public void saveScore(ScorePrimitive potentialHighscore) {
        highscores.add(potentialHighscore);
        highscores.sort(Comparator.comparingInt(ScorePrimitive::getScore).reversed());
        if (highscores.size() > 5) {
            highscores = highscores.subList(0, 5);
        }
        saveAllScores();
    }

    public ScorePrimitiveList getHighscores() {
        return new ScorePrimitiveList(highscores);
    }

    private void loadHighscores() {
        highscores = new JSONPersistence(ioAdapter).loadUnsafe(persistenceKey).getScorePrimitiveList();
        if (highscores == null) {
            highscores = new ArrayList<>();
        }
    }

    private void saveAllScores() {
        new JSONPersistence(ioAdapter).save(new ScorePrimitiveList(highscores), persistenceKey);
    }
}
