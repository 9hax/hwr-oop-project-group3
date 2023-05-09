package hwr.oop;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;

public class BowlingThrowTest {
    @Test
    void testSetFallenPins(){
        //given
        Throw throw = new Throw();
        //when
        throw.setFallenPins(3);
        //then
        assertThat(throw.getPins()).isEqualTo(3);
    }

}
