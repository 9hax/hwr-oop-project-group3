package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PlayerTest {
    private final String name = "Anna";
    private final Integer currentPoints = 29;
    private final BowlingStates strike = BowlingStates.STRIKE;
    private final LinkedList<Round> rounds = new LinkedList<Round>(Arrays.asList(new Round(1, 10),
            new Round(2, 7),new Round(3, 10),
            new Round(4, 9),new Round(5, 8),
            new Round(6, 10),new Round(7, 10),
            new Round(8, 2),new Round(9, 9),
            new Round(10 ,6)));
    private final LinkedList<Integer> extraRounds = new LinkedList<Integer>(Arrays.asList(1,2, 3, 4, 6, 7, 7, 8));

    private Player player = new Player(name, currentPoints, rounds, extraRounds);

    @Test
    void newPlayerTest() {
        LinkedList<Round> testRounds = new LinkedList<Round>(List.of(new Round(1, 10)));
        LinkedList<Integer> testExtraRounds = new LinkedList<Integer>(List.of(1,2, 3, 4, 6, 7, 7, 8));
        Player testPlayer = new Player("Anna", 29,testRounds,testExtraRounds );
        Assertions.assertThat(testPlayer.name).isEqualTo(player.name);
        Assertions.assertThat(testPlayer.currentPoints).isEqualTo(player.currentPoints);
        Assertions.assertThat(testPlayer.extraRounds).containsExactlyInAnyOrderElementsOf(player.extraRounds);
        Assertions.assertThat(testPlayer.rounds).filteredOn(
                r -> r.getRoundNumber().equals(rounds.get(0).getRoundNumber())
                        && r.roundPoints.equals(rounds.get(0).roundPoints));
    }
    @Test
    void calculatePlayerResultsTest() {
        player.calculatePlayerResults(player);
        Integer finalPoints = 97;
        Assertions.assertThat(player.currentPoints).isEqualTo(finalPoints);
    }
    @Test
    void addExtraRoundTest() {
        Player testPlayer = new Player(name, currentPoints, rounds, new LinkedList<Integer>());
        Round strikeRound = rounds.get(0);
        strikeRound.playedState = strike;
        final List<Integer> extraStrikeRounds = List.of(1, 2);
        testPlayer.addExtraRound(testPlayer, strikeRound);
        Assertions.assertThat(testPlayer.getExtraRounds()).containsExactlyInAnyOrderElementsOf(extraStrikeRounds);
    }
    @Test
    void throwExtraThrowTest() {
        Round lastRound = rounds.get(rounds.size()-1);
        Round otherRound = rounds.get(3);
        int otherRoundPoints = player.throwExtraThrow(strike, otherRound);
        Assertions.assertThat(otherRoundPoints).isEqualTo(0);
        int normalPoints = player.throwExtraThrow(BowlingStates.NORMAL, lastRound);
        Assertions.assertThat(normalPoints).isEqualTo(0);
    }
    @Test
    void playRoundTest() {
        Round round = rounds.get(2);
        player.playRound(player, round);
        LinkedList<Integer> extraRoundsForCurrentStrike = new LinkedList<Integer>(List.of(3, 4));
        Assertions.assertThat(round.playedState).isEqualTo(strike);
        Assertions.assertThat(round.roundPoints).isEqualTo(rounds.get(2).roundPoints);
        Assertions.assertThat(player.rounds).containsExactlyInAnyOrderElementsOf(rounds);
        Assertions.assertThat(player.extraRounds).containsExactlyInAnyOrderElementsOf(extraRoundsForCurrentStrike);
        Assertions.assertThat(player.currentPoints).isEqualTo(currentPoints+rounds.get(2).roundPoints);

        Round lastRound = rounds.get(9);
        player.playRound(player, lastRound);
        Assertions.assertThat(lastRound.playedState).isEqualTo(BowlingStates.NORMAL);
        Assertions.assertThat(lastRound.roundPoints).isEqualTo(rounds.get(9).roundPoints);
        Assertions.assertThat(player.rounds).containsExactlyInAnyOrderElementsOf(rounds);
        Assertions.assertThat(player.extraRounds).isNullOrEmpty();
        Assertions.assertThat(player.currentPoints).isEqualTo(45);
    }


}
