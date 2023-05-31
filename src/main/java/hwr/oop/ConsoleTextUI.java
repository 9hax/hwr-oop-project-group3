package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class ConsoleTextUI implements TextUI {
    final IOAdapter ioAdapter;
    Game game;

    public ConsoleTextUI(IOAdapter ioAdapter) {
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
        game = new Game(playerNames);
        return game;
    }

    private void playOnce() {
        //TODO printScores(), add SinglePlayerMode
        Player player = game.getCurrentPlayer();
        while (player == game.getCurrentPlayer()) {
            playPlayerRound(player);
        }
    }

    @Override
    public void playRound() {
        ioAdapter.putString(Integer.toString(game.getRound()));
        int currentRound = game.getRound();
        while (currentRound == game.getRound()) {
            playOnce();
        }
    }

    private void playPlayerRound(Player player) {
        ioAdapter.putString("How many Pins did " + player.getName() + " hit? >");
        String input = ioAdapter.getString();
        int hitPins;
        try {
            hitPins = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            hitPins = 0;
        }
        player.throwBall(hitPins);
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