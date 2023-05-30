package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class ConsoleUI implements UI {
    final IOAdapter ioAdapter;

    public ConsoleUI(IOAdapter ioAdapter) {
        this.ioAdapter = ioAdapter;
    }

    @Override
    public Game createGame() {
        ioAdapter.putString("Please input the names of each player. " +
                "Please press ENTER after each name." +
                "Press ENTER twice if you have written down all the player names");
        List <String> playerNames = inputStringList();
        ioAdapter.putString("Registered players: "+
                playerNames.toString().replace("[", "").replace("]",""));
        return new Game(playerNames);
    }

    private List<String> inputStringList(){
        List<String> inputs = new ArrayList<>();
        boolean isDone = false;
        while (!isDone){
            String input = ioAdapter.getString();
            if(input.isEmpty()){
                isDone = true;
            }
            else{
                inputs.add(input);
            }
        }
        return inputs;
    }
}