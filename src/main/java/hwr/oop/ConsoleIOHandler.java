package hwr.oop;

import java.util.Scanner;

public class ConsoleIOHandler {
    Scanner globalScanner;
    boolean isTesting = false;

    /**
     * Initializes a new IOHandler.
     *
     * @param isInTesting If the system is to be tested, the IO is handled differently to make automatic testing work.
     */
    public ConsoleIOHandler(boolean isInTesting) {
        globalScanner = new Scanner(System.in);
    }

    public void StringOut(String out) {
        System.out.print(out);
    }

    public String StringIn() {
        return globalScanner.nextLine();
    }

    public void ErrorOut(String out) {
        System.err.print(out);
    }

}
