package hwr.oop;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import java.util.Collections;

class BowlingBowlingThrowTest {
    @Test
    void testSetFallenPinsInt_ValidPinsFallen(){
        //given
        BowlingThrow bowlThrow = new BowlingThrow();

        bowlThrow.setFallenPins(3);
        assertThat(bowlThrow.getFallenPins()).isEqualTo(3);

        bowlThrow.setFallenPins(0);
        assertThat(bowlThrow.getFallenPins()).isZero();
        bowlThrow.setFallenPins(10);
        assertThat(bowlThrow.getFallenPins()).isEqualTo(10);
    }

    @Test
    void testSetFallenPinsInt_InvalidPinsFallen(){
        //given
        BowlingThrow bowlThrow = new BowlingThrow();
        //then
        assertThatThrownBy(() -> bowlThrow.setFallenPins(15)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bowlThrow.setFallenPins(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bowlThrow.setFallenPins(11)).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void testSetFallenPinsInt_negativeNumber(){
        //given
        BowlingThrow bowlThrow = new BowlingThrow();
        //when

        //then
        assertThatThrownBy(() -> bowlThrow.setFallenPins(-1)).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void testGetFallenPinsInt(){
        //given
        BowlingThrow bowlThrow = new BowlingThrow();
        bowlThrow.setFallenPins(3);
        //when
        int pins = bowlThrow.getFallenPins();
        //then
        assertThat(pins).isEqualTo(3);
    }

    @Test
    void testGetFallenPinsString(){
        //given
        BowlingThrow bowlThrow = new BowlingThrow();
        bowlThrow.setFallenPins("3");
        //when
        String pins = bowlThrow.getFallenPinsString();
        //then
        assertThat(pins).isEqualTo("3");
    }
    @Test
    void testSetFallenPinsString_ValidPinsFallen(){
        //given
        BowlingThrow bowlThrow = new BowlingThrow();

        bowlThrow.setFallenPins("3");
        assertThat(bowlThrow.getFallenPinsString()).isEqualTo("3");

        bowlThrow.setFallenPins("-");
        assertThat(bowlThrow.getFallenPinsString()).isEqualTo("-");
        bowlThrow.setFallenPins("10");
        assertThat(bowlThrow.getFallenPinsString()).isEqualTo("10");
    }

    @Test
    void testSetFallenPinsString_InvalidPinsFallen(){
        //given
        BowlingThrow bowlThrow = new BowlingThrow();
        //then
        assertThatThrownBy(() -> bowlThrow.setFallenPins("15")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bowlThrow.setFallenPins("-1")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bowlThrow.setFallenPins("11")).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void getPointsFromFallenPinsString_Test() {
        BowlingThrow bowlThrow = new BowlingThrow();

        bowlThrow.setFallenPins("3");
        assertThat(bowlThrow.getFallenPins()).isEqualTo(1);

    }
}
