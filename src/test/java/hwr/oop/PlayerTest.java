package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    @Test
    void createPlayerWithName_getPlayerName() {
        Player namedPlayer = new Player("Steve");
        assertThat(namedPlayer.getName()).isEqualTo("Steve");
    }

    @Test
    void createPlayer_hasTenRounds() {
        Player player = new Player("Steve");
        List<Round> rounds = player.getRounds();
        assertThat(rounds.size()).isEqualTo(10);
    }


}
