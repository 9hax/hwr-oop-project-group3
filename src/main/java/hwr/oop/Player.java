package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class Player {


    private final String name;
    private final List<Round> rounds;

    public Player(String name) {
        this.name = name;
        rounds = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Round> getRounds() {
        return this.rounds;
    }


    public boolean throwBall(int i) {

    }
}
