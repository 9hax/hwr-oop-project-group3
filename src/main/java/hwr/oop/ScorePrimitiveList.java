package hwr.oop;

import java.util.List;

public class ScorePrimitiveList {

    List<ScorePrimitive> scorePrimitives;
    ScorePrimitiveList(List<ScorePrimitive> scorePrimitives) {
        this.scorePrimitives = scorePrimitives;
    }

    List<ScorePrimitive> getScorePrimitiveList() {
        return scorePrimitives;
    }
}
