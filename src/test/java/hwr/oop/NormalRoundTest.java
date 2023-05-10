package hwr.oop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NormalRoundTest{

    @Test
    void createEmptyRoundTest() {
        Round round = new NormalRound();
        assertThat(round.getPoints()).isEqualTo(0);
    }

    @Test
    void getPointsTest(){
        List<Throw> normalThrowList = List.of(new Throw(1), new Throw(3));
        Round round = new NormalRound(normalThrowList);
        assertThat(round.getPoints()).isEqualTo(4);
    }

    @Test
    void checkValidityTest() {
        // Test the boundaries
        List<Throw> validThrowList = List.of(new Throw(0), new Throw(0));
        assertThat(new NormalRound(validThrowList).getPoints()).isEqualTo(0);

        validThrowList = List.of(new Throw(5), new Throw(5));
        assertThat(new NormalRound(validThrowList).getPoints()).isEqualTo(10);

        // The following throws an excception, as 11 pins are hit according to the throwList.
        List<Throw> invalidThrowList = List.of(new Throw(6), new Throw(6));
        assertThatThrownBy(() -> new NormalRound(invalidThrowList)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkIsStrikeTest() {
        List<Throw> strikeThrow = List.of(new Throw(10));
        Round strikeRound = new NormalRound(strikeThrow);
        boolean isStrike = strikeRound.isStrike();
        assertThat(isStrike).isTrue();
    }

    @Test
    void checkIsNotStrikeTest() {
        List<Throw> notStrikeThrows = List.of(new Throw(9));
        Round notStrikeRound = new NormalRound(notStrikeThrows);
        boolean isStrike = notStrikeRound.isStrike();
        assertThat(isStrike).isFalse();
    }


}
