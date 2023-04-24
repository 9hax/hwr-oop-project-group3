package hwr.oop;

import java.awt.event.KeyEvent;
import java.util.*;
import java.util.stream.Collectors;

public class Game {
    Scanner gameScanner = new Scanner(System.in);
    List<Player> players = new ArrayList<>();

    int numberOfPeople;

    private void gameProcess(KeyEvent e) {
        /** **
         **  Asks how many people will play the game
         **/
        System.out.println("How many people will play the game?");
        numberOfPeople = gameScanner.nextInt();


        /** **
         **  Asks who will play the game. Converts to a string array.
         **  @param The names of the players.
         **  @return Array of players
         **/
        System.out.println("Who will play?");
        String playersNames = gameScanner.nextLine();
        players = createPlayers(playersNames);

        /** **
         **  Waits for user to begin the name
         **  @param answer or keyEvent, if the user does not want to begin or did not
         *                 type a valid character
         **  @return game starts.
         **/
        System.out.println("Would you like to start? Y/N");
        while (true) {
            String answer = gameScanner.nextLine();

            if (answer.equals("Y")) {
                System.out.println("Lets begin!");
                break;
            } else if (answer.equals("N")) {
                System.out.println("Press 'enter' if you want to start.");
                while (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        System.out.println("Let's begin!");
                        break;
                    }
                }
            } else {
                System.out.println("Type a valid character.");
            }
        }
        /** **
         **  loops through ten rounds and the amount of players. Asks which pins gt hit.
         * Rounds get initialized with the current round number and points.
         * If a strike occurred, it won`t ask for a second input.
         **  @param round number, round points
         **  @return game is getting played.
         **/
        for (int rN = 0; rN<10; rN++){
            for(Player player:players){
                Round round = new Round(rN, 0);
                System.out.println("Which pins got hit?");
                String throwScanOne = gameScanner.nextLine();
                round.roundPoints = round.countThrowPoints(throwScanOne);
                player.playRound(player, round);
                if(!round.setState(1, round.roundPoints).equals(BowlingStates.STRIKE)){
                    String throwScanTwo = gameScanner.nextLine();
                    round.roundPoints += round.countThrowPoints(throwScanTwo);
                    player.playRound(player, round);
                }
            }
        }
        /** **
         **  We got our winner!.
         **  @param List of players
         **  @return The game winner player.
         **/
        getWinner(players);
    }

    /** **
     ** We generate a Player List.
     **  @param playersNames the typed in names of the players.
     **  @return List of players.
     **/
    List<Player> createPlayers(String playersNames){
        for (String playerName : playersNames.split(" ")) {
            players.add(new Player(playerName, 0, new LinkedList<Round>(), new LinkedList<Integer>()));
        }
        return players;
    }
    /** **
     ** We calculate the results, print them out and get the winner. The winner name and their points
     * get printed out.
     **  @param players the players in the game.
     **  @return the game winner
     **/
    Player getWinner(List<Player> players){
        for (Player player : players) {
            player.calculatePlayerResults(player);
            System.out.println(player.name + " with " + player.currentPoints);
        }
        Integer result = players.stream().mapToInt(Player::getCurrentPoints).max().orElse(0);

        Player winner = players.stream().filter((player)->player.currentPoints.equals(result)).toList().get(0);
        System.out.println("The winner is " + winner.name + " with " + winner.currentPoints + " points.\n " + "Congratulations!");
        return winner;
    }
    public static void main(String[] args){
        Game game = new Game();
        KeyEvent e = null;
        game.gameProcess(e);
    }
}

