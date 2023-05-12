package hwr.oop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    @Test
    void createPlayerWithName_getPlayerName() {
        Player namedPlayer = new Player("Steve");
        assertThat(namedPlayer.getName()).isEqualTo("Steve");
    }

}
