package hwr.oop;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;

public class BowlingThrowTest {
    @Test
    void testSetFallenPins(){
        //given
        Throw bowlThrow = new Throw();
        //when
        bowlThrow.setFallenPins(3);
        //then
        assertThat(bowlThrow.getFallenPins()).isEqualTo(3);
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
