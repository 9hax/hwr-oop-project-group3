package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class ScorePrimitiveList {
    public ScorePrimitiveList(Game game) {
        this.scorePrimitives = new ArrayList<>();
        for(Player player: game.getPlayers()){
            scorePrimitives.add(new ScorePrimitive(player.getName(), player.getPlayerPoints()));
        }
    }

    List<ScorePrimitive> scorePrimitives;
    ScorePrimitiveList(List<ScorePrimitive> scorePrimitives) {
        this.scorePrimitives = scorePrimitives;
    }

    List<ScorePrimitive> getScorePrimitiveList() {
        return scorePrimitives;
    }


}
