package hwr.oop;

public interface PersistenceAdapter {
    void save(Object persistentObject, String key);

    void save(Object persistentObject);

    ScorePrimitiveList load(String key);

    ScorePrimitiveList load();
}
