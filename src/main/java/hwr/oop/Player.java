package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class Player {


    private String name;
    private List<Round> rounds;

    public Player(String name) {
        this.name = name;
        rounds = new ArrayList<>();
        for( int i = 0; i<10; i++) {
            rounds.add(new NormalRound());
        }
    }

    public String getName() {
        return name;
    }

    public List<Round> getRounds() {
        return this.rounds;
    }
}
