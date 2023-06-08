package hwr.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighscoreHandler {
    private final IOAdapter ioAdapter;
    private final String persistenceKey;
    private List<ScorePrimitive> highScores;

    public HighscoreHandler(IOAdapter ioAdapter, String persistenceKey) {
        this.ioAdapter = ioAdapter;
        this.persistenceKey = persistenceKey;
        loadHighScores();
    }

    public void clearHighscores() {
        highScores = new ArrayList<>();
        saveAllScores();
    }

    public void saveScore(ScorePrimitive potentialHighscore) {
        highScores.add(potentialHighscore);
        highScores.sort(Comparator.comparingInt(ScorePrimitive::getScore).reversed());
        if (highScores.size() > 5) {
            highScores = highScores.subList(0, 5);
        }
        saveAllScores();
    }

    public ScorePrimitiveList getHighScores() {
        return new ScorePrimitiveList(highScores);
    }

    private void loadHighScores() {
        highScores = new JSONPersistence(ioAdapter).loadUnsafe(persistenceKey).getScorePrimitiveList();
    }

    private void saveAllScores() {
        new JSONPersistence(ioAdapter).save(new ScorePrimitiveList(highScores), persistenceKey);
    }
}
