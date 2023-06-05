package hwr.oop;
import java.util.Scanner;

public class ConsoleIOAdapter implements IOAdapter {
    @Override
    public String getString() {
        return new Scanner(System.in).nextLine();
    }

    @Override
    @SuppressWarnings("java:S106")
    public void putString(String outputString) {
        System.out.println(outputString);
    }

    @Override
    public void queueInput(String inputString) {
        throw new NonMockableClassException("The queueInput Mocking method has been called in a non-mockable class.");
    }

    @Override
    public String pollOutput() {
        throw new NonMockableClassException("The pollOutput Mocking method has been called in a non-mockable class.");
    }

    @Override
    public String lastOutput() {
        throw new NonMockableClassException("The pollOutput Mocking method has been called in a non-mockable class.");
    }

    @Override
    public void trimOutputQueue(Integer i) {
        throw new NonMockableClassException("The trimOutputQueue Mocking method has been called in a non-mockable class.");
    }

    @Override
    public void ignoreOutputs(Integer i) {
        throw new NonMockableClassException("The ignoreOutputs Mocking method has been called in a non-mockable class.");
    }
}
class NonMockableClassException extends RuntimeException {
    public NonMockableClassException(String errorMessage) {
        super(errorMessage);
    }
}


