package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class GameTest {

    private final Game game = new Game();

    @Test
    void createPlayersTest() {
        List<Player> players= List.of(new Player("Anna",0, new LinkedList<Round>(),new LinkedList<Integer>()),
                new Player("Annika",0, new LinkedList<Round>(),new LinkedList<Integer>()),
                new Player("Friedrich",0, new LinkedList<Round>(), new LinkedList<Integer>()));
        String playerNames = "Anna Annika Friedrich";
        List<Player> testPlayers = game.createPlayers(playerNames);
        for(int i  = 0;i< testPlayers.size(); i++ ){
            Assertions.assertThat(testPlayers.get(i).name).isEqualTo(players.get(i).name);
            Assertions.assertThat(testPlayers.get(i).currentPoints).isEqualTo(players.get(i).currentPoints);
            Assertions.assertThat(testPlayers.get(i).rounds).isNullOrEmpty();
            Assertions.assertThat(testPlayers.get(i).extraRounds).isNullOrEmpty();
        }

    }
    @Test
    void getWinnerTest() {
        List<Player> players= List.of(new Player("Anna",10, new LinkedList<Round>(),new LinkedList<Integer>()),
                new Player("Annika",50, new LinkedList<Round>(),new LinkedList<Integer>()),
                new Player("Friedrich",80, new LinkedList<Round>(), new LinkedList<Integer>()));
        Player winner = players.get(2);
        Player player = game.getWinner(players);
        Assertions.assertThat(player.name).isEqualTo(winner.name);
        Assertions.assertThat(player.currentPoints).isEqualTo(winner.currentPoints);
    }
}
