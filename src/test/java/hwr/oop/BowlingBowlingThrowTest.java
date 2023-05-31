package hwr.oop;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class BowlingBowlingThrowTest {
    @Test
    void testSetFallenPins_ValidPinsFallen(){
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
    void testSetFallenPins_InvalidPinsFallen(){
        //given
        BowlingThrow bowlThrow = new BowlingThrow();
        //then
        assertThatThrownBy(() -> bowlThrow.setFallenPins(15)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bowlThrow.setFallenPins(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bowlThrow.setFallenPins(11)).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void testSetFallenPins_negativeNumber(){
        //given
        BowlingThrow bowlThrow = new BowlingThrow();
        //when

        //then
        assertThatThrownBy(() -> bowlThrow.setFallenPins(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testGetFallenPins(){
        //given
        BowlingThrow bowlThrow = new BowlingThrow();
        bowlThrow.setFallenPins(3);
        //when
        int pins = bowlThrow.getFallenPins();
        //then
        assertThat(pins).isEqualTo(3);
    }
}
