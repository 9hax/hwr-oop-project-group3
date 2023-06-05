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
                "Please press ENTER after each name. " +
                "Press ENTER twice if you have written down all the player names. \n>");
        List <String> playerNames = inputStringList();
        ioAdapter.putString("Registered players: "+
                playerNames.toString().replace("[", "").replace("]",""));
        game = new Game(playerNames);
        return game;
    }

    private void allPlayersPlayOneRound() {
        boolean playNextRound = true;
        while (playNextRound) {
            playNextRound = playPlayerRound(game.getCurrentPlayer());
        }
        printScores();
    }

    @Override
    public void playRound() {
        ioAdapter.putString("Current round is #" + (game.getRound()+1));
        int currentRound = game.getRound();
        while (currentRound == game.getRound()) {
            allPlayersPlayOneRound();
        }
    }

    @Override
    public void playGame() {
        ioAdapter.putString("The game starts now!");
        for(int round = 0; round<= 9; round++){
            playRound();
        }
        ioAdapter.putString("Game is finished!");

        ioAdapter.putString("The winner is "+ game.determineWinner().getName() +
                " with "+ game.determineWinner().getPlayerPoints()+ " points. CongratulaZZ1ONES!");
        
        ioAdapter.putString("The Scores are the following:");
        for (Player player :
                game.getPlayers()) {
            ioAdapter.putString(player.getName() + " scored " + player.getPlayerPoints() + " points.");
        }
    }


    private boolean playPlayerRound(Player player) {
        ioAdapter.putString("How many pins did " + player.getName() + " hit? >");
        String input = ioAdapter.getString();
        int hitPins;
        try {
            hitPins = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            hitPins = 0;
        }
        boolean continuePlay = player.throwBall(hitPins);
        if (continuePlay){
            ioAdapter.putString(player.getName() + " hit " + hitPins + " pins and throws again.");
        }
        else {
            ioAdapter.putString(player.getName() + " hit " + hitPins + " pins and has finished this round.");
        }
        return continuePlay;
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

    private void printScores(){
        List <Player> players = game.getPlayers();
        for (Player player :
                players) {
            ioAdapter.putString(player.getName() + " has scored " + player.getPlayerPoints() + " points.");
        }
    }
}