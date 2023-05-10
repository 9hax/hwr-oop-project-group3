package hwr.oop;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class BowlingThrowTest {
    @Test
    void testSetFallenPins_3PinsFallen(){
        //given
        Throw bowlThrow = new Throw();
        //when
        bowlThrow.setFallenPins(3);
        //then
        assertThat(bowlThrow.getFallenPins()).isEqualTo(3);
    }

    @Test
    void testSetFallenPins_15PinsFallen(){
        //given
        Throw bowlThrow = new Throw();
        //when

        //then
        assertThatThrownBy(() -> bowlThrow.setFallenPins(15)).isInstanceOf(IllegalArgumentException.class);
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
