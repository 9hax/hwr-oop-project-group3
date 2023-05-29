package hwr.oop;

import jdk.jshell.spi.ExecutionControl;

import java.util.Scanner;

public class ConsoleIOAdapter implements IOAdapter {
    @Override
    public String getString() {
        return new Scanner(System.in).nextLine();
    }

    @Override
    @SuppressWarnings("java:S106")
    public void putString(String outputString) {
        System.out.print(outputString);
    }

    @Override
    public void queueInput(String inputString) {
        try {
            throw new ExecutionControl.NotImplementedException("ConsoleIO not been called with mock data");
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String pullOutput() {
        return null;
    }
}
