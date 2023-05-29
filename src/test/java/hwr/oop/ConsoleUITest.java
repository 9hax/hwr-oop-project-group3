package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConsoleUITest {
    @Test
    void initialiseTest() {
        ConsoleUI consoleUI = new ConsoleUI();
        Assertions.assertThat(consoleUI).isNotNull();
    }
}
