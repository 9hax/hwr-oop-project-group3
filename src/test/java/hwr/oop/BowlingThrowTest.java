package hwr.oop;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class BowlingThrowTest {
    @Test
    void testSetFallenPins_ValidPinsFallen(){
        //given
        Throw bowlThrow = new Throw();

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
        Throw bowlThrow = new Throw();
        //then
        assertThatThrownBy(() -> bowlThrow.setFallenPins(15)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bowlThrow.setFallenPins(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bowlThrow.setFallenPins(11)).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void testSetFallenPins_negativeNumber(){
        //given
        Throw bowlThrow = new Throw();
        //when

        //then
        assertThatThrownBy(() -> bowlThrow.setFallenPins(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testGetFallenPins(){
        //given
        Throw bowlThrow = new Throw();
        bowlThrow.setFallenPins(3);
        //when
        int pins = bowlThrow.getFallenPins();
        //then
        assertThat(pins).isEqualTo(3);
    }
}
