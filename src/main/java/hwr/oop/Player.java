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

    public void addRound(Round round) {
        if (!rounds.isEmpty()) {
            round.setPreviousRound(rounds.get(rounds.size()-1));
        }
        rounds.add(round);

    }




}
