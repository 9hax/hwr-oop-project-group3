package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NormalRoundTest {

    @Test
    void createEmptyRoundTest() {
        Round round = new NormalRound();
        assertThat(round.getPoints()).isEqualTo(0);
    }

    @Test
    void getPointsTest() {
        List<Throw> normalThrowList = List.of(new Throw(1), new Throw(3));
        Round round = new NormalRound(normalThrowList);
        assertThat(round.getPoints()).isEqualTo(4);
    }

    @Test
    void playNormalRound_hasNoBonusPoints() {
        List<Throw> normalThrowList = List.of(new Throw(1), new Throw(3));
        Round round = new NormalRound(normalThrowList);
        round.calculateBonusPoints();
        assertThat(round.getBonusPoints()).isEqualTo(0);
    }

    @Test
    void playStrikeRound_get7BonusPoints() {
        List<Throw> strikeRoundThrows = List.of(new Throw(10));
        List<Throw> secondRoundThrows = List.of(new Throw(3), new Throw(4));
        Round strikeRound = new NormalRound(strikeRoundThrows);
        Round secondRound = new NormalRound(secondRoundThrows, strikeRound);
        strikeRound.prepareBonusCounter();
        secondRound.prepareBonusCounter();
        secondRound.calculateBonusPoints();
        int bonusPoints = strikeRound.getBonusPoints();
        assertThat(bonusPoints).isEqualTo(7);
    }

    @Test
    void playSpareRound_get3BonusPoints() {
        List<Throw> strikeRoundThrows = List.of(new Throw (4), new Throw (6));
        List<Throw> secondRoundThrows = List.of(new Throw(3), new Throw(4));
        Round strikeRound = new NormalRound(strikeRoundThrows);
        Round secondRound = new NormalRound(secondRoundThrows, strikeRound);
        strikeRound.prepareBonusCounter();
        secondRound.prepareBonusCounter();
        secondRound.calculateBonusPoints();
        int bonusPoints = strikeRound.getBonusPoints();
        assertThat(bonusPoints).isEqualTo(3);
    }

    @Test
    void checkValidityTest() {
        // Test the boundaries
        List<Throw> validThrowList = List.of(new Throw(0), new Throw(0));
        NormalRound validRound = new NormalRound(validThrowList);
        assertThat(validRound.getPoints()).isEqualTo(0);

        validThrowList = List.of(new Throw(5), new Throw(5));
        assertThat(new NormalRound(validThrowList).getPoints()).isEqualTo(10);

        // The following throws an excception, as 11 pins are hit according to the throwList.
        List<Throw> invalidThrowList = List.of(new Throw(6), new Throw(6));
        assertThatThrownBy(() -> new NormalRound(invalidThrowList)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() ->new NormalRound(invalidThrowList, validRound)).isInstanceOf(IllegalArgumentException.class);
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

    @Test
    void checkIsSpareTest() {
        List<Throw> spareThrows = List.of(new Throw(6), new Throw(4));
        Round spareRound = new NormalRound(spareThrows);
        boolean isSpare = spareRound.isSpare();
        assertThat(isSpare).isTrue();
    }

    @Test
    void setPreviousSpareTest(){
        List<Throw> firstRoundThrows = List.of(new Throw(4), new Throw(6));
        Round firstRound = new NormalRound(firstRoundThrows);

        List<Throw> secondRoundThrows = List.of(new Throw(4), new Throw(2));
        Round secondRound = new NormalRound(secondRoundThrows, firstRound);

        secondRound.setPreviousRound(firstRound);
        secondRound.calculateBonusPoints();

        assertThat(firstRound.getBonusPoints()).isEqualTo(4);
    }

    @Test
    void throw3PinsThrow6Pins_noSpare() {
        List<Throw> noSpareThrows = List.of(new Throw(3), new Throw(6));
        Round noSpareRound = new NormalRound(noSpareThrows);
        boolean isSpare = noSpareRound.isSpare();
        assertThat(isSpare).isFalse();
    }

    @Test
    void throw10Pins_noSpare() {
        List<Throw> spareThrow = List.of(new Throw(10));
        Round strikeRound = new NormalRound(spareThrow);
        boolean isSpare = strikeRound.isSpare();
        assertThat(isSpare).isFalse();
    }

    @Test
    void throw0PinsThrow10Pins_noStrike() {
        List<Throw> notStrikeThrows = List.of(new Throw(0), new Throw(10));
        Round notStrikeRound = new NormalRound(notStrikeThrows);
        boolean isStrike = notStrikeRound.isStrike();
        assertThat(isStrike).isFalse();
    }

    @Test
    void throw3PinsThrow5Pins_8Points() {
        List<Throw> eightPointsThrow = List.of(new Throw(3), new Throw(5));
        Round eightPointsRound = new NormalRound(eightPointsThrow);
        int points = eightPointsRound.getPoints();
        assertThat(points).isEqualTo(8);
    }

    @Test
    void throw3and5_throw4and3_noBonusPointsInFirstRound() {
        List<Throw> firstRoundThrows = List.of(new Throw(3), new Throw(5));
        List<Throw> secondRoundThrows = List.of(new Throw(3), new Throw(4));
        Round firstRound = new NormalRound(firstRoundThrows);
        Round secondRound = new NormalRound(secondRoundThrows, firstRound);
        int bonusPoints = secondRound.getBonusPoints();
        assertThat(bonusPoints).isEqualTo(0);
    }

    @Test
    void throwTwoStrikes_throw3and4_get13BonusPointsInFirstRound(){
        List<Throw> firstRoundThrows = List.of(new Throw(10));
        List<Throw> secondRoundThrows = List.of(new Throw(10));
        List<Throw> thirdRoundThrows = List.of(new Throw(3), new Throw(4));

        Round firstRound = new NormalRound(firstRoundThrows);
        Round secondRound = new NormalRound(secondRoundThrows, firstRound);
        Round thirdRound = new NormalRound(thirdRoundThrows, secondRound);
        thirdRound.calculateBonusPoints();
        int bonusPointsFirstRound = firstRound.getBonusPoints();
        int bonusPointsSecondRound = secondRound.getBonusPoints();

        assertThat(bonusPointsFirstRound).isEqualTo(13);
        assertThat(bonusPointsSecondRound).isEqualTo(7);
    }

    @Test
    void createThreeRounds_convertToList() {
        Round firstRound = new NormalRound(List.of(new Throw(0), new Throw(1)));
        Round secondRound = new NormalRound(List.of(new Throw(0), new Throw(2)), firstRound);
        Round thirdRound = new NormalRound(List.of(new Throw(0), new Throw(3)), secondRound);

        ArrayList<Round> roundList = thirdRound.convertToList();
        ArrayList<Round> roundTestList = new ArrayList<Round>(List.of(firstRound, secondRound, thirdRound));

        assertThat(roundList).isEqualTo(roundTestList);

    }
}
