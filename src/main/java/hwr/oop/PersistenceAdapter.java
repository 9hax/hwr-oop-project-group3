package hwr.oop;

public interface PersistenceAdapter {
    void save(ScorePrimitiveList scores, String key);

    void save(ScorePrimitiveList scores);

    ScorePrimitiveList load(String key);

    ScorePrimitiveList load();

    ScorePrimitiveList loadUnsafe(String key);
}
