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
        String output = outputQueue.poll();
        if (output == null) {
            throw new QueueEmptyException("The output queue was empty when polled.");
        }
        return output;
    }

    @Override
    public String lastOutput() {
        String lastOutput = outputQueue.getLast();
        outputQueue = new LinkedList<>();
        return lastOutput;
    }

    @Override
    public void trimOutputQueue(Integer i) {
        if(outputQueue.size() < i) {
            throw new IndexOutOfBoundsException("The output queue is not long enough.");
        }
        while (outputQueue.size() > i) {
            outputQueue.poll();
        }
    }

    @Override
    public void ignoreOutputs(Integer count) {
        if(outputQueue.size() < count) {
            throw new IndexOutOfBoundsException("The output queue is not long enough.");
        }
        for (int i = 0; i < count; i++) {
            outputQueue.poll();
        }
    }

}

class QueueEmptyException extends RuntimeException {
    public QueueEmptyException(String errorMessage) {
        super(errorMessage);
    }
}

