package hwr.oop;

public interface IOAdapter {
    String getString();

    void putString(String outputString);

    void queueInput(String inputString);

    String pollOutput();

    String lastOutput();

    void trimOutputQueue(Integer i);
}
