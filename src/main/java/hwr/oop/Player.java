package hwr.oop;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Player {
    int score = 0;
    int currentMultiplier = 0;
    String name = "Default Player Name";
    public List<Round> rounds;
    public List<Integer> extraRounds;
    public Integer currentPoints;

    public Player(String name, Integer currentPoints, LinkedList<Round> rounds, LinkedList<Integer> extraRounds) {
        this.name = name;
        this.currentPoints = currentPoints;
        this.rounds = rounds;
        this.extraRounds = extraRounds;
    }
    /** **
     ** The player plays the game and the parameters (round points,round number, the points of the
     * current player, the rounds of the player, the extra rounds for later) get changed. The relevant results get
     * printed out.
     **  @param player and a round currently.
     **  @return We play the game.
     **/
    public void playRound(Player player, Round round) {
        System.out.println("Round " + round.getRoundNumber() + "  " + player.name + " is playing." + "\n They have " +
                player.currentPoints + " points.\n First throw");

        round.playedState = round.setState(1, round.getRoundPoints());
        player.addExtraRound(player, round);
        round.setRoundNumber(throwExtraThrow(round.playedState, round)+ round.getRoundPoints());
        if (!round.playedState.equals(BowlingStates.STRIKE)) {
            round.playedState = round.setState(2, round.getRoundPoints());
            player.addExtraRound(player, round);
            round.setRoundPoints(throwExtraThrow(round.playedState, round)+ round.getRoundPoints());
            if (round.playedState.equals(BowlingStates.NORMAL)) {
                System.out.println((10 - round.getRoundPoints()) + " pins left. " + round.getRoundPoints() + " points added");
            }
        }
        player.rounds.add(round);
        player.setCurrentPoints(player.getCurrentPoints()+round.getRoundPoints());
    }

    /** **
     ** The player results get calculated. Since we already have the final points in the final round, our
     * function has to add the bonus points from the STRIKE and SPARE rounds
     **  @param player which is playing the round .
     **  @return The final points get set.
     **/

    public void calculatePlayerResults(Player player){
        for(Integer extraRound: player.extraRounds){
            player.currentPoints+=player.rounds.get(extraRound-1).roundPoints;;
        }
    }
    /** **
     ** We add the bonus rounds, from which we're later going to retrieve the bonus points. But in only happens from round
     * 1-9. Strikes give two rounds, spares one. The state of the throw also gets printed out
     **  @param player and a round current.
     **  @return  A new bonus round gets added to the current player.
     **/
    void addExtraRound(Player player, Round round) {
        if(round.getRoundNumber()<9){
            List<Integer> extraRounds = new ArrayList<>();
            if (round.playedState.equals(BowlingStates.STRIKE)) {
                System.out.println("Strike! " + 10 + " points added");
                extraRounds.add(round.getRoundNumber());
                extraRounds.add(round.getRoundNumber() + 1);
            } else if (round.playedState.equals(BowlingStates.SPARE)) {
                System.out.println("Spare! " + 10 + " points added");
                extraRounds.add(round.getRoundNumber());
            }
            player.setExtraRounds(extraRounds);
        }
    }

    /** **
     ** If we are at round ten the rules change. If a strike gets hit the player gets to additional throws.
     * With a spare one additional throw. The player gets informed about the status of their throw and asked
     * to throw again if a strike or spare happened.
     **  @param state of the last round and the curren round
     **  @return  The points gained in the 10th round.
     **/

    int throwExtraThrow(BowlingStates state, Round round){
        Game game = new Game();
        int points = 0;
        if(round.getRoundNumber()>8){
            if(state.equals(BowlingStates.STRIKE)){
                System.out.println("You have two additional throws.");
                for (int th = 0; th < 2; th++) {
                    System.out.println("Throw number " + (th + 1));
                    String throwScanOne = game.gameScanner.nextLine();
                    points = round.countThrowPoints(throwScanOne);
                }
            }else if(state.equals(BowlingStates.SPARE)){
                System.out.println("You have one additional throw.");
                String throwScanOne = game.gameScanner.nextLine();
                points = round.countThrowPoints(throwScanOne);
            }
        }
        return points;
    }
}


