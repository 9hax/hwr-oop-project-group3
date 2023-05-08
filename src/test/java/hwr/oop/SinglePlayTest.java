package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SinglePlayTest {
    @Test
    void testConvertHitPins_noPinsHit(){
        //given
        int[] pinIndices = new int[] {};

        //when
        boolean[] allBooleansFalse = SinglePlay.convertHitPins(pinIndices);

        //then
        for (int i=0;i<= allBooleansFalse.length-1;i++) {
            assertFalse(allBooleansFalse[i]);
        }
    }

    @Test
    void testConvertHitPins_allPinsHit(){
        //given
        int[] pinIndices = new int[] {1,2,3,4,5,6,7,8,9,10};

        //when
        boolean[] allBooleansTrue = SinglePlay.convertHitPins(pinIndices);

        //then
        for (int i=0;i<=allBooleansTrue.length-1;i++) {
            assertTrue(allBooleansTrue[i]);
        }
    }

    @Test
    void testConvertHitPins_allUnevenPinsHit() {
        //given
        int[] pinIndices = new int[]{1, 3, 5, 7, 9};

        //when
        boolean[] allUnevenBooleansTrue = SinglePlay.convertHitPins(pinIndices);

        //then
        for (int i = 0; i <= allUnevenBooleansTrue.length - 1; i++) {
            assertTrue(allUnevenBooleansTrue[i]);
            i++;
        }
    }

    @Test
    void testConvertHitPins_allEvenPinsHit() {
        //given
        int[] pinIndices = new int[]{2,4,6,8,10};

        //when
        boolean[] allEvenBooleansTrue = SinglePlay.convertHitPins(pinIndices);

        //then
        for (int i = 1; i <= allEvenBooleansTrue.length - 1; i++) {
            assertTrue(allEvenBooleansTrue[i]);
            i++;
        }
    }

    @Test
    void testAllHit_ReturnTrue(){
        //given
        boolean[] pinState = new boolean[10];
        Arrays.fill(pinState, Boolean.FALSE);
        //when
        boolean booleanTrue = SinglePlay.allHit(pinState);

        //then
        assertTrue(booleanTrue);
    }

    @Test
    void testAllHit_ReturnFalse(){
        //given
        boolean[] pinState = new boolean[10];
        Arrays.fill(pinState, Boolean.TRUE);

        //when
        boolean booleanFalse = SinglePlay.allHit(pinState);

        //then
        assertFalse(booleanFalse);
    }
}
