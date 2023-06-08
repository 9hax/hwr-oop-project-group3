package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class ConsoleTextUI implements TextUI {
    final IOAdapter ioAdapter;
    Game game;

    int tempHitPins;

    public ConsoleTextUI(IOAdapter ioAdapter) {
        this.ioAdapter = ioAdapter;
    }

    @Override
    public Game createGame() {
        ioAdapter.putString("Please input the names of each player. " +
                "Please press ENTER after each name. " +
                "Press ENTER twice if you have written down all the player names. \n>");
        List <String> playerNames = sanitizePlayerName(inputStringList());
        ioAdapter.putString("Registered players: "+
                playerNames.toString().replace("[", "").replace("]",""));
        game = new Game(playerNames);
        return game;
    }

    private void allPlayersPlayOneRound() {
        boolean playNextRound = true;
        ioAdapter.putString("It's " + game.getCurrentPlayer().getName()+ "'s turn.");
        while (playNextRound) {
            playNextRound = playPlayerRound(game.getCurrentPlayer());
        }
        printScores();
    }

    @Override
    public void playRound() {
        printRoundInfo();
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
        if (askSave()) saveGame(game);
    }

    @Override
    public boolean askRestart() {
        ioAdapter.putString("Input Y to play another game. \n>");
        return ioAdapter.getString().equals("Y");
    }

    boolean askSave() {
        ioAdapter.putString("Input S to save the current scores.");
        return ioAdapter.getString().equals("S");
    }

    void saveGame(Game game) {
        new JSONPersistence(ioAdapter).save(new ScorePrimitiveList(game));
    }


    private boolean playPlayerRound(Player player) {
        boolean throwSuccessful = false;
        boolean continuePlay = false;

        while (!throwSuccessful) {
            try {
                continuePlay = doThrow(player);
                throwSuccessful = true;
            } catch (IllegalArgumentException ex) {
                ioAdapter.putString("Invalid input! Please try again.");
                printRoundInfo();
                if (player.getTempRound() == null) {
                    ioAdapter.putString("This is the first throw this round.");
                } else {
                    ioAdapter.putString("This is the second throw this round.");
                }
            }
        }
        announceScoring(continuePlay, player);
        return continuePlay;
    }

    private boolean doThrow(Player player) {
        ioAdapter.putString("How many pins did " + player.getName() + " hit? >");
        String input = ioAdapter.getString();
        try {
            tempHitPins = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            tempHitPins = 0;
        }
        return player.throwBall(tempHitPins);
    }

    private void announceScoring(boolean continuePlay, Player player) {
        if (continuePlay){
            ioAdapter.putString(player.getName() + " hit " + tempHitPins + " pins and throws again.");
        }
        else {
            ioAdapter.putString(player.getName() + " hit " + tempHitPins + " pins and has finished this round.");
            if (player.getLastPlayedRound().isStrike()){
                ioAdapter.putString(player.getName() + " just scored a STRIKE!");
            }
            if (player.getLastPlayedRound().isSpare()) {
                ioAdapter.putString(player.getName() + " just scored a SPARE!");
            }
        }
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

    private List <String> sanitizePlayerName(List <String> names){
        List <String> sanitizedNameList = new ArrayList<>();
        for (String name :
                names) {
            sanitizedNameList.add(name.strip());
        }
        return sanitizedNameList;
    }

    private void printRoundInfo() {
        ioAdapter.putString("Current round is #" + (game.getRound()+2));
    }

    public void showHighScores(){
        ioAdapter.putString("======== HIGHSCORES ========");
        int i = 1;
        for (ScorePrimitive sp: new HighscoreHandler(ioAdapter, "highscores").getHighScores().getScorePrimitiveList()
             ) {
            ioAdapter.putString(i + ". Place: " + sp.getName() + " with " + sp.getScore() + " points");
            i++;
        }
    }
}