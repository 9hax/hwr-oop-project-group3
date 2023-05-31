package hwr.oop;

import java.util.LinkedList;
import java.util.Queue;

public class MockIOAdapter implements IOAdapter {
    final Queue<String> inputQueue;
    LinkedList<String> outputQueue;

    public MockIOAdapter() {
        inputQueue = new LinkedList<>();
        outputQueue = new LinkedList<>();
    }
    @Override
    @SuppressWarnings("java:S106")
    public String getString() {
        if(!inputQueue.isEmpty()){
            String inputString = inputQueue.poll();
            System.out.println("Polled from input: " + inputString);
            return inputString;
        }
        return "";
    }

    @Override
    @SuppressWarnings("java:S106")
    public void putString(String outputString) {
        System.out.println(outputString);
        outputQueue.add(outputString);
    }

    public void queueInput(String inputString){
        inputQueue.add(inputString);
    }

    public String pollOutput(){
        return  outputQueue.poll();
    }

    @Override
    public String lastOutput() {
        String lastOutput = outputQueue.getLast();
        outputQueue = new LinkedList<>();
        return lastOutput;
    }

}
