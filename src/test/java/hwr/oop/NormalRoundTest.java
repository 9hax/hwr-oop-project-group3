package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class NormalRoundTest {
    @Test
    void testIfSpare_allPinsHit(){
        //given
        boolean[] pinsState = new boolean[10];
        Arrays.fill(pinsState, Boolean.FALSE);
        NormalRound round = new NormalRound();

        //when
        boolean isSpare = round.ifSpare(pinsState);

        //then
        assertTrue(isSpare);
    }

    @Test
    void testIfSpare_noPinsHit(){
        //given
        boolean[] pinsState = new boolean[10];
        Arrays.fill(pinsState, Boolean.TRUE);
        NormalRound round = new NormalRound();

        //when
        boolean isSpare = round.ifSpare(pinsState);

        //then
        assertFalse(isSpare);
    }

    @Test
    void testIfStrike_noPinsHit(){
        //given
        boolean[] pinsState = new boolean[10];
        Arrays.fill(pinsState, Boolean.TRUE);
        NormalRound round = new NormalRound();

        //when
        boolean isStrike = round.ifStrike(pinsState);

        //then
        assertFalse(isStrike);
    }


    @Test
    void testIfStrike_allPinsHit(){
        //given
        boolean[] pinsState = new boolean[10];
        Arrays.fill(pinsState, Boolean.FALSE);
        NormalRound round = new NormalRound();

        //when
        boolean isStrike = round.ifStrike(pinsState);

        //then
        assertTrue(isStrike);
    }
}
