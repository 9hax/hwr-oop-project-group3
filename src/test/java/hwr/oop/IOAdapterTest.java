package hwr.oop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class IOAdapterTest {

    @Test
    void createConsoleIOAdapterTest() {
        IOAdapter ioAdapter = new ConsoleIOAdapter();
        assertThat(ioAdapter).isNotNull();
    }
    @Test
    void createMockIOTest() {
        IOAdapter ioAdapter = new MockIOAdapter();
        assertThat(ioAdapter).isNotNull();
    }
    @Test
    void test_input() {
        IOAdapter ioAdapter = new ConsoleIOAdapter();
        String inputString = "This is a test string.";
        String testInput = String.format(inputString, System.lineSeparator(), System.lineSeparator());
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));

        String returned = ioAdapter.getString();

        assertThat(returned).isEqualTo(inputString);
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

        assertThat(lastOutput).isEqualTo(outputString);
    }

    @Test
    void testInputMock() {
        IOAdapter ioAdapter = new MockIOAdapter();
        String inputString = "This is a test string.";
        assertThat(ioAdapter.getString()).isEmpty();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        ioAdapter.queueInput(inputString);
        String returned = ioAdapter.getString();

        String[] outputLines = outputStream.toString().split(System.lineSeparator());
        String lastOutput = outputLines[outputLines.length-1];

        assertThat(lastOutput).isEqualTo("Polled from input: " + inputString);


        assertThat(returned).isEqualTo(inputString);
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

        assertThat(lastOutput).isEqualTo(outputString);
        lastOutput = ioAdapter.pollOutput();

        assertThat(lastOutput).isEqualTo(outputString);
    }

    @Test
    void testConsoleMockingException(){
        IOAdapter ioAdapter = new ConsoleIOAdapter();

        assertThatThrownBy(() ->ioAdapter.queueInput("")).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(ioAdapter::pollOutput).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(ioAdapter::lastOutput).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() ->ioAdapter.trimOutputQueue(null)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void test_lastOutputMock() {
        IOAdapter ioAdapter = new MockIOAdapter();
        String outputString = "This is test string.";
        String outputString2 = "This is another test string.";

        ioAdapter.putString(outputString);
        ioAdapter.putString(outputString2);

        String lastOutput = ioAdapter.lastOutput();

        assertThat(lastOutput).isEqualTo(outputString2);
    }

    @Test
    void trimOutputQueue() {
        IOAdapter ioAdapter = new MockIOAdapter();
        for(int i = 0; i<9; i++) {
            ioAdapter.putString(Integer.toString(i));
        }
        ioAdapter.trimOutputQueue(2);
        assertThat(ioAdapter.pollOutput()).isEqualTo("7");
        assertThat(ioAdapter.pollOutput()).isEqualTo("8");

        assertThatThrownBy(() -> ioAdapter.trimOutputQueue(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }
}
