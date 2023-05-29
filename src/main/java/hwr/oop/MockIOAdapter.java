package hwr.oop;

import java.util.LinkedList;
import java.util.Queue;

public class MockIOAdapter implements IOAdapter {
    Queue<String> inputQueue;
    Queue<String> outputQueue;

    public MockIOAdapter() {
        inputQueue = new LinkedList<>();
        outputQueue = new LinkedList<>();
    }
    @Override
    public String getString() {
        if(!inputQueue.isEmpty()){
            return inputQueue.poll();
        }
        return "";
    }

    @Override
    public void putString(String outputString) {
        outputQueue.add(outputString);
    }

    public void queueInput(String inputString){
        inputQueue.add(inputString);
    }

    public String pullOutput(){
        return  outputQueue.poll();
    }

}
