package hwr.oop;

import java.awt.event.KeyEvent;
import java.util.*;
public class Game {
    Scanner gameScanner = new Scanner(System.in);
    List<Player> players = new ArrayList<>();

    int numberOfPeople;

    private final int pinsTotal = 10;

    private void gameProcess(KeyEvent e) {
        //asked how many players will play
        System.out.println("How many people will play the game?");
        numberOfPeople = gameScanner.nextInt();

        //players get added
        System.out.println("Who will play?");
        String playersNames = gameScanner.nextLine();
        players = createPlayers(playersNames);

        //wait for start
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

        for (int roundNumber = 0; roundNumber < 9; roundNumber++) {
            for (Player player : players) {
                //print roundNumber, the current points of a player and the plaxer name and the
                //throw number
                System.out.println("Round " + roundNumber + "  " + player.name + " is playing." + "\n They have " +
                        player.currentPoints + " points.\n First throw");
                int points = countPoints(convertArray(gameScanner.nextLine()));
                //points get converted from user input
                Round currentRound = new Round(roundNumber, points);
                currentRound.playNormalRound(setState(1, points), player);
                //currentRound state gets set
                if (!currentRound.getPlayedState().equals(BowlingStates.STRIKE)) {
                    //points in the second throw get converted
                    points += countPoints(convertArray(gameScanner.nextLine()));
                    currentRound = currentRound.playNormalRound(setState(2, points), player);
                    if (currentRound.getPlayedState().equals(BowlingStates.NORMAL)) {
                        player.currentPoints += currentRound.roundPoints;
                        //points in the second throw get added and the left out pins get printed
                        System.out.println((pinsTotal - currentRound.getRoundPoints()) + " pins left. " + points + " points added");
                    }
                }
            }
        }
        Round lastRound = new Round(10, 0);
        for (Player player : players) {
            //Prints out round an player name
            System.out.println("Last round! " + player.name + " is playing " + "\n Round 10" + "\n Which pins did you hit in the first throw ?");
            int points = countPoints(convertArray(gameScanner.nextLine()));
            //points get converted from player input
            BowlingStates state = setState(1, points);
            //state gets set
            if (state.equals(BowlingStates.STRIKE)) {
                //if strike player gets two extra throws
                System.out.println(player.name + " has two additional throws.");
                for (int th = 0; th < 2; th++) {
                    System.out.println("Throw number " + (th + 1));
                    points += countPoints(convertArray(gameScanner.nextLine()));
                }
            } else {
                //player gets asked which pins in second throw
                System.out.println("Pins left: " + (pinsTotal - points) + "\n Which pins got hit in the second throw?");
                points += countPoints(convertArray(gameScanner.nextLine()));
                state = setState(2, points);
                if (state.equals(BowlingStates.SPARE)) {
                    //if spare player gets one extra throw
                    System.out.println(player.name + " has one additional throw.");
                    points += countPoints(convertArray(gameScanner.nextLine()));
                } else {
                    //pins left printed out
                    System.out.println((pinsTotal - points) + " pins left. " + points + " points added");
                }
            }
            //last round defined and added to playerRounds
            lastRound = lastRound.playRoundTen(state, points);
            player.rounds.add(lastRound);
        }
        // player results get calculated and printed out
        for (Player player : players) {
            player.calculatePlayerResults(player);
            System.out.println(player.name + " with " + player.currentPoints);
        }
        //winner with name and points printed out
        System.out.println("The winner is " + getWinner(players).name + " with " + getWinner(players).currentPoints + " points.\n " + "Congratulations!");

    }
    BowlingStates setState(int th, Integer points) {
        BowlingStates state;
        if(points == pinsTotal && th==1){
            state = BowlingStates.STRIKE;
        }else if(points == pinsTotal && th==2){
            state = BowlingStates.SPARE;
        }else{
            state = BowlingStates.NORMAL;
        }
        return state;
    }
    List<Boolean> convertArray(String scan){
        List<Boolean> pins = Arrays.asList(true, true, true, true, true, true, true, true, true, true);
        List<String> pinsHit = List.of(scan.split(" "));
        if(scan.equals("-")){
            return pins;
        }
        for (String pin : pinsHit) {
            int hitPin = Integer.parseInt(pin);
            if (hitPin < 0 || hitPin > 10) {
                System.out.println("Type a valid number.");
                break;
            }
            if (pins.get(hitPin - 1).equals(false)) {
                System.out.println("Pin already got hit. Type a valid pin.");
                break;
            }
            pins.set(hitPin - 1, false);
        }
        return pins;
    }
    List<Player> createPlayers(String playersNames){
        for (String playerName : playersNames.split(" ")) {
            players.add(new Player(playerName, 0, new ArrayList<Round>(), new ArrayList<Integer>()));
        }
        return players;
    }
    int countPoints(List<Boolean> pins){
        return Collections.frequency(pins, false);
    }
    Player getWinner(List<Player> players){
        Integer result = players.stream().map(Player::getCurrentPoints).sorted().toList().get(0);
        return players.stream().filter((player)->player.currentPoints.equals(result)).toList().get(0);
    }
    public static void main(String[] args){
        Game game = new Game();
        KeyEvent e = null;
        game.gameProcess(e);
    }
}

