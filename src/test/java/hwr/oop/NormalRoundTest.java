package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NormalRoundTest {

    @Test
    void getPointsTest(){
        List<Throw> normalThrowList = List.of(new Throw(1), new Throw(3));
        Round round = new NormalRound(normalThrowList);
        assertThat(round.getPoints()).isEqualTo(4);
    }
}
