package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class TenthRoundTest {

    @Test
    void createEmptyRoundTest() {
        TenthRound round = new TenthRound();
        assertThat(round.getPoints()).isZero();
    }

    @Test
    void addThrow_getNewPoints() {
        List <BowlingThrow> singleThrowList = List.of(new BowlingThrow(3));
        TenthRound unfinishedRound = new TenthRound(singleThrowList);
        unfinishedRound.addThrow(new BowlingThrow(4));
        int points = unfinishedRound.getPoints();
        assertThat(points).isEqualTo(7);
    }

    @Test
    void getPointsTest() {
        List<BowlingThrow> normalThrowList = List.of(new BowlingThrow(1), new BowlingThrow(3));
        TenthRound round = new TenthRound(normalThrowList);
        assertThat(round.getPoints()).isEqualTo(4);
    }

    @Test
    void playStrikeRound_getBonusThrows() {
        List<BowlingThrow> strikeRoundThrows = List.of(new BowlingThrow(10));
        TenthRound strikeRound = new TenthRound(strikeRoundThrows);
        int normalPoints = strikeRound.getPoints();
        assertThat(normalPoints).isEqualTo(10);
        assertThat(strikeRound.getExtraThrows()).isEqualTo(2);
    }

    @Test
    void playSpareRound_get3BonusPoints() {
        List<BowlingThrow> spareRoundThrows = List.of(new BowlingThrow(4), new BowlingThrow(6));
        TenthRound spareRound = new TenthRound(spareRoundThrows);

        assertThat(spareRound.isSpare()).isTrue();
        assertThat(spareRound.getPoints()).isEqualTo(10);
        assertThat(spareRound.getExtraThrows()).isEqualTo(1);
    }

    @Test
    void checkValidityTest() {
        // Test the boundaries of normal round
        List<BowlingThrow> validThrowListNormal = List.of(new BowlingThrow(0), new BowlingThrow(0));
        TenthRound validRoundNormal = new TenthRound(validThrowListNormal);
        assertThat(validRoundNormal.getPoints()).isZero();

        validThrowListNormal = List.of(new BowlingThrow(5), new BowlingThrow(5));
        assertThat(new TenthRound(validThrowListNormal).getPoints()).isEqualTo(10);

        // The following throws an exception, as 11 pins are hit according to the throwList.
        List<BowlingThrow> invalidThrowListNormal = List.of(new BowlingThrow(6), new BowlingThrow(6));
        assertThatThrownBy(() -> new TenthRound(invalidThrowListNormal)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() ->new TenthRound(invalidThrowListNormal, validRoundNormal)).isInstanceOf(IllegalArgumentException.class);

        // Test the boundaries of spare round
        List<BowlingThrow> validThrowListStrike = List.of(new BowlingThrow(9), new BowlingThrow(1),new BowlingThrow(0));
        TenthRound validRoundSpare = new TenthRound(validThrowListStrike);
        assertThat(validRoundSpare.getPoints()).isEqualTo(10);

        validThrowListStrike = List.of(new BowlingThrow(10), new BowlingThrow(10),new BowlingThrow(9));
        assertThat(new TenthRound(validThrowListStrike).getPoints()).isEqualTo(29);

        // The following throws an exception, as 11 pins are hit according to the throwList.
        List<BowlingThrow> invalidThrowListStrike = List.of(new BowlingThrow(10), new BowlingThrow(10), new BowlingThrow(10));
        assertThatThrownBy(() -> new TenthRound(invalidThrowListStrike)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() ->new TenthRound(invalidThrowListStrike, validRoundSpare)).isInstanceOf(IllegalArgumentException.class);
    }



    @Test
    void checkIsStrikeTest() {
        List<BowlingThrow> strikeThrow = List.of(new BowlingThrow(10));
        TenthRound strikeRound = new TenthRound(strikeThrow);
        boolean isStrike = strikeRound.isStrike();
        assertThat(isStrike).isTrue();
    }

    @Test
    void checkIsNotStrikeTest() {
        List<BowlingThrow> notStrikeThrows = List.of(new BowlingThrow(9));
        TenthRound notStrikeRound = new TenthRound(notStrikeThrows);
        boolean isStrike = notStrikeRound.isStrike();
        assertThat(isStrike).isFalse();
    }

    @Test
    void checkIsSpareTest() {
        List<BowlingThrow> spareThrows = List.of(new BowlingThrow(6), new BowlingThrow(4));
        Round spareRound = new TenthRound(spareThrows);
        boolean isSpare = spareRound.isSpare();
        assertThat(isSpare).isTrue();
    }

    @Test
    void throw3PinsThrow6Pins_noSpare() {
        List<BowlingThrow> noSpareThrows = List.of(new BowlingThrow(3), new BowlingThrow(6));
        Round noSpareRound = new TenthRound(noSpareThrows);
        boolean isSpare = noSpareRound.isSpare();
        assertThat(isSpare).isFalse();
    }

    @Test
    void throw10Pins_noSpare() {
        List<BowlingThrow> spareThrow = List.of(new BowlingThrow(10));
        Round strikeRound = new TenthRound(spareThrow);
        boolean isSpare = strikeRound.isSpare();
        assertThat(isSpare).isFalse();
    }

    @Test
    void throw0PinsThrow10Pins_noStrike() {
        List<BowlingThrow> notStrikeThrows = List.of(new BowlingThrow(0), new BowlingThrow(10));
        Round notStrikeRound = new TenthRound(notStrikeThrows);
        boolean isStrike = notStrikeRound.isStrike();
        assertThat(isStrike).isFalse();
    }

    @Test
    void throw3PinsThrow5Pins_8Points() {
        List<BowlingThrow> eightPointsThrow = List.of(new BowlingThrow(3), new BowlingThrow(5));
        Round eightPointsRound = new TenthRound(eightPointsThrow);
        int points = eightPointsRound.getPoints();
        assertThat(points).isEqualTo(8);
    }


    @Test
    void createThreeRounds_convertToList() {
        TenthRound firstRound = new TenthRound(List.of(new BowlingThrow(0), new BowlingThrow(1)));
        TenthRound secondRound = new TenthRound(List.of(new BowlingThrow(0), new BowlingThrow(2)), firstRound);
        Round thirdRound = new TenthRound(List.of(new BowlingThrow(0), new BowlingThrow(3)), secondRound);

        ArrayList<Round> roundList = thirdRound.convertToList();
        ArrayList<Round> roundTestList = new ArrayList<>(List.of(firstRound, secondRound, thirdRound));

        assertThat(roundList).isEqualTo(roundTestList);
    }

    @Test
    void playStrikeRound_getBonusPointCalculationCounter(){
        NormalRound firstRound = new NormalRound(List.of(new BowlingThrow(10)));
        assertThat(firstRound.getBonusPointCalculationCounter()).isEqualTo(2);
    }
}
