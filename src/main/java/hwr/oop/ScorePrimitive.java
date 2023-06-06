package hwr.oop;

public class ScorePrimitive {

    private final String name;
    private final Integer score;
    ScorePrimitive(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    String getName() {
        return name;
    }

    Integer getScore() {
        return score;
    }
}
