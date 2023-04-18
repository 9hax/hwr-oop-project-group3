package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

// TODO Delete this placeholder test.
class UserInterfaceTest {
    @Test
    void test_input() {
        // This test makes an input and checks if the returned value matches.
        String inputString = "This is a test string.";
        String testInput = String.format(inputString, System.lineSeparator(), System.lineSeparator());
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));

        String returned = UserInterface.promptUser();

        Assertions.assertThat(returned.equals(inputString)).isTrue();
    }

    @Test
    void test_output() {
        // This makes a test Output and checks if the returned value matches.
        String outputString = "This is another test string.";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        UserInterface.sendOutput(outputString);

        String[] outputLines = outputStream.toString().split(System.lineSeparator());
        String lastOutput = outputLines[outputLines.length-1];

        Assertions.assertThat(lastOutput.equals(outputString)).isTrue();
    }

    @Test
    void test_parse_list() {
        // Return a sorted list of unsanitized user input numbers.
        Assertions.assertThat(
                Arrays.equals(
                        UserInterface.parseInputNumberList(",1,0, 5 ,4, 3,2,6,7,8,9,"),
                        new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
                ).isTrue();
    }
}