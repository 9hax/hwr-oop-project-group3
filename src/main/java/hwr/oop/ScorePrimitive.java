package hwr.oop;

public class ScorePrimitive {

    private String name;
    private Integer score;
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
