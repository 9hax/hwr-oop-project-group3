package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class IOAdapterTest {

    @Test
    void createConsoleIOAdapterTest() {
        IOAdapter ioAdapter = new ConsoleIOAdapter();
    }
    @Test
    void createMockIOTest() {
        IOAdapter ioAdapter = new MockIOAdapter();
    }
    @Test
    void test_input() {
        IOAdapter ioAdapter = new ConsoleIOAdapter();
        String inputString = "This is a test string.";
        String testInput = String.format(inputString, System.lineSeparator(), System.lineSeparator());
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));

        String returned = ioAdapter.getString();

        Assertions.assertThat(returned).isEqualTo(inputString);
    }

    @Test
    void test_output() {
        IOAdapter ioAdapter = new ConsoleIOAdapter();
        String outputString = "This is another test string.";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        ioAdapter.putString(outputString);

        String[] outputLines = outputStream.toString().split(System.lineSeparator());
        String lastOutput = outputLines[outputLines.length-1];

        Assertions.assertThat(lastOutput).isEqualTo(outputString);
    }

    @Test
    void testInputMock() {
        IOAdapter ioAdapter = new MockIOAdapter();
        String inputString = "This is a test string.";

        String returned = ioAdapter.getString();

        Assertions.assertThat(returned).isEqualTo(inputString);
    }

    @Test
    void test_outputMock() {
        IOAdapter ioAdapter = new MockIOAdapter();
        String outputString = "This is another test string.";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        ioAdapter.putString(outputString);

        String[] outputLines = outputStream.toString().split(System.lineSeparator());
        String lastOutput = outputLines[outputLines.length-1];

        Assertions.assertThat(lastOutput).isEqualTo(outputString);
    }
}
