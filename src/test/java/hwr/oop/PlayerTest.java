package hwr.oop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    @Test
    void createPlayerWithName_getPlayerName() {
        Player namedPlayer = new Player("Steve");
        assertThat(namedPlayer.getName()).isEqualTo("Steve");
    }

    @Test
    void playerPlaysStrikeThenNormalRound_getGameStatus() {
        Player namedPlayer = new Player("Steve");
        boolean continueRound = namedPlayer.throwBall(10);

        assertThat(continueRound).isFalse();
        assertThat(namedPlayer.getLastPlayedRound().getPoints()).isEqualTo(10);
        Round lastPlayedRound = namedPlayer.getLastPlayedRound();
        continueRound = namedPlayer.throwBall(5);
        assertThat(continueRound).isTrue();
        continueRound = namedPlayer.throwBall(3);
        assertThat(continueRound).isFalse();
        assertThat(namedPlayer.getLastPlayedRound().getPreviousRound()).isEqualTo(lastPlayedRound);
    }

    @Test
    void playUnfinishedRound_getTempRound(){
        Player namedPlayer = new Player("Steve");
        boolean continueRound = namedPlayer.throwBall(5);

        assertThat(continueRound).isTrue();
        assertThat(namedPlayer.getTempRound().getPoints()).isEqualTo(5);
        continueRound = namedPlayer.throwBall(3);
        assertThat(continueRound).isFalse();
        assertThat(namedPlayer.getLastPlayedRound().getPoints()).isEqualTo(8);
    }

    @Test
    void calculatePlayerHasNoPoints() {
        Player namedPlayer = new Player("Steve");
        assertThat(namedPlayer.getPlayerPoints()).isZero();
    }
    @Test
    void calculatePlayerPoints() {
        Player namedPlayer = new Player("Steve");
        boolean continueRound = namedPlayer.throwBall(5);
        continueRound = namedPlayer.throwBall(3);
        assertThat(namedPlayer.getPlayerPoints()).isEqualTo(8);

        continueRound = namedPlayer.throwBall(2);
        continueRound = namedPlayer.throwBall(7);
        assertThat(namedPlayer.getPlayerPoints()).isEqualTo(17);
    }

    @Test
    void playStrike_getPlayerPoints() {
        Player namedPlayer = new Player("Dudududedudude-du Steve");
        boolean continueRound = namedPlayer.throwBall(10);

        assertThat(namedPlayer.getPlayerPoints()).isEqualTo(10);

        continueRound = namedPlayer.throwBall(5);
        continueRound = namedPlayer.throwBall(4);

        int playerPoints = namedPlayer.getPlayerPoints();
        assertThat(playerPoints).isEqualTo(28);
    }

    //TODO make everything package private
}
