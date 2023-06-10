package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NormalRoundTest {

    @Test
    void createEmptyRoundTest() {
        NormalRound round = new NormalRound();
        assertThat(round.getPoints()).isZero();
    }

    @Test
    void addThrow_getNewPoints() {
        List <BowlingThrow> singleThrowList = List.of(new BowlingThrow(3));
        NormalRound unfinishedRound = new NormalRound(singleThrowList);
        unfinishedRound.addThrow(new BowlingThrow(4));
        int points = unfinishedRound.getPoints();
        assertThat(points).isEqualTo(7);
    }

    @Test
    void getPointsTest() {
        List<BowlingThrow> normalThrowList = List.of(new BowlingThrow(1), new BowlingThrow(3));
        NormalRound round = new NormalRound(normalThrowList);
        assertThat(round.getPoints()).isEqualTo(4);
    }

    @Test
    void playNormalRound_hasNoBonusPoints() {
        List<BowlingThrow> normalThrowList = List.of(new BowlingThrow(1), new BowlingThrow(3));
        NormalRound round = new NormalRound(normalThrowList);
        round.calculateBonusPoints();
        assertThat(round.getBonusPoints()).isZero();
    }

    @Test
    void playStrikeRound_get7BonusPoints() {
        List<BowlingThrow> strikeRoundThrows = List.of(new BowlingThrow(10));
        List<BowlingThrow> secondRoundThrows = List.of(new BowlingThrow(3), new BowlingThrow(4));
        NormalRound strikeRound = new NormalRound(strikeRoundThrows);
        NormalRound secondRound = new NormalRound(secondRoundThrows, strikeRound);
        strikeRound.prepareBonusCounter();
        secondRound.prepareBonusCounter();
        secondRound.calculateBonusPoints();
        int bonusPoints = strikeRound.getBonusPoints();
        assertThat(bonusPoints).isEqualTo(7);

        int normalPoints = strikeRound.getPoints();
        assertThat(normalPoints).isEqualTo(17);
    }

    @Test
    void playSpareRound_get3BonusPoints() {
        List<BowlingThrow> spareRoundThrows = List.of(new BowlingThrow(4), new BowlingThrow(6));
        List<BowlingThrow> secondRoundThrows = List.of(new BowlingThrow(3), new BowlingThrow(4));
        NormalRound strikeRound = new NormalRound(spareRoundThrows);
        NormalRound secondRound = new NormalRound(secondRoundThrows, strikeRound);
        strikeRound.prepareBonusCounter();
        secondRound.prepareBonusCounter();
        secondRound.calculateBonusPoints();
        int bonusPoints = strikeRound.getBonusPoints();
        assertThat(bonusPoints).isEqualTo(3);
    }

    @Test
    void checkValidityTest() {
        // Test the boundaries
        List<BowlingThrow> validThrowList = List.of(new BowlingThrow(0), new BowlingThrow(0));
        NormalRound validRound = new NormalRound(validThrowList);
        assertThat(validRound.getPoints()).isZero();

        validThrowList = List.of(new BowlingThrow(5), new BowlingThrow(5));
        assertThat(new NormalRound(validThrowList).getPoints()).isEqualTo(10);

        // The following throws an exception, as 11 pins are hit according to the throwList.
        List<BowlingThrow> invalidThrowList = List.of(new BowlingThrow(6), new BowlingThrow(6));
        assertThatThrownBy(() -> new NormalRound(invalidThrowList)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() ->new NormalRound(invalidThrowList, validRound)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkValidityAddThrow(){
        List<BowlingThrow> validThrowList = List.of(new BowlingThrow(7));
        NormalRound unfinishedRound = new NormalRound(validThrowList);

        assertThatThrownBy(()-> unfinishedRound.addThrow(new BowlingThrow(7))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkIsStrikeTest() {
        List<BowlingThrow> strikeThrow = List.of(new BowlingThrow(10));
        Round strikeRound = new NormalRound(strikeThrow);
        boolean isStrike = strikeRound.isStrike();
        assertThat(isStrike).isTrue();
    }

    @Test
    void checkIsNotStrikeTest() {
        List<BowlingThrow> notStrikeThrows = List.of(new BowlingThrow(9));
        Round notStrikeRound = new NormalRound(notStrikeThrows);
        boolean isStrike = notStrikeRound.isStrike();
        assertThat(isStrike).isFalse();
    }

    @Test
    void checkIsSpareTest() {
        List<BowlingThrow> spareThrows = List.of(new BowlingThrow(6), new BowlingThrow(4));
        Round spareRound = new NormalRound(spareThrows);
        boolean isSpare = spareRound.isSpare();
        assertThat(isSpare).isTrue();
    }

    @Test
    void setPreviousSpareTest(){
        List<BowlingThrow> firstRoundThrows = List.of(new BowlingThrow(4), new BowlingThrow(6));
        NormalRound firstRound = new NormalRound(firstRoundThrows);

        List<BowlingThrow> secondRoundThrows = List.of(new BowlingThrow(4), new BowlingThrow(2));
        NormalRound secondRound = new NormalRound(secondRoundThrows, firstRound);

        secondRound.setPreviousRound(firstRound);
        secondRound.calculateBonusPoints();

        assertThat(secondRound.getPreviousRound()).isEqualTo(firstRound);
        assertThat(firstRound.getBonusPoints()).isEqualTo(4);
    }

    @Test
    void throw3PinsThrow6Pins_noSpare() {
        List<BowlingThrow> noSpareThrows = List.of(new BowlingThrow(3), new BowlingThrow(6));
        NormalRound noSpareRound = new NormalRound(noSpareThrows);
        boolean isSpare = noSpareRound.isSpare();
        assertThat(isSpare).isFalse();
    }

    @Test
    void throw10Pins_noSpare() {
        List<BowlingThrow> spareThrow = List.of(new BowlingThrow(10));
        NormalRound strikeRound = new NormalRound(spareThrow);
        boolean isSpare = strikeRound.isSpare();
        assertThat(isSpare).isFalse();
    }

    @Test
    void throw0PinsThrow10Pins_noStrike() {
        List<BowlingThrow> notStrikeThrows = List.of(new BowlingThrow(0), new BowlingThrow(10));
        NormalRound notStrikeRound = new NormalRound(notStrikeThrows);
        boolean isStrike = notStrikeRound.isStrike();
        assertThat(isStrike).isFalse();
    }

    @Test
    void throw3PinsThrow5Pins_8Points() {
        List<BowlingThrow> eightPointsThrow = List.of(new BowlingThrow(3), new BowlingThrow(5));
        NormalRound eightPointsRound = new NormalRound(eightPointsThrow);
        int points = eightPointsRound.getPoints();
        assertThat(points).isEqualTo(8);
    }

    @Test
    void throw3and5_throw4and3_noBonusPointsInFirstRound() {
        List<BowlingThrow> firstRoundThrows = List.of(new BowlingThrow(3), new BowlingThrow(5));
        List<BowlingThrow> secondRoundThrows = List.of(new BowlingThrow(3), new BowlingThrow(4));
        NormalRound firstRound = new NormalRound(firstRoundThrows);
        NormalRound secondRound = new NormalRound(secondRoundThrows, firstRound);
        int bonusPoints = secondRound.getBonusPoints();
        assertThat(bonusPoints).isZero();
    }

    @Test
    void throwTwoStrikes_throw3and4_get13BonusPointsInFirstRound(){
        List<BowlingThrow> firstRoundThrows = List.of(new BowlingThrow(10));
        List<BowlingThrow> secondRoundThrows = List.of(new BowlingThrow(10));
        List<BowlingThrow> thirdRoundThrows = List.of(new BowlingThrow(3), new BowlingThrow(4));

        NormalRound firstRound = new NormalRound(firstRoundThrows);
        NormalRound secondRound = new NormalRound(secondRoundThrows, firstRound);
        NormalRound thirdRound = new NormalRound(thirdRoundThrows, secondRound);
        thirdRound.calculateBonusPoints();
        int bonusPointsFirstRound = firstRound.getBonusPoints();
        int bonusPointsSecondRound = secondRound.getBonusPoints();

        assertThat(bonusPointsFirstRound).isEqualTo(13);
        assertThat(bonusPointsSecondRound).isEqualTo(7);
    }

    @Test
    void createThreeRounds_convertToList() {
        NormalRound firstRound = new NormalRound(List.of(new BowlingThrow(0), new BowlingThrow(1)));
        NormalRound secondRound = new NormalRound(List.of(new BowlingThrow(0), new BowlingThrow(2)), firstRound);
        NormalRound thirdRound = new NormalRound(List.of(new BowlingThrow(0), new BowlingThrow(3)), secondRound);

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
