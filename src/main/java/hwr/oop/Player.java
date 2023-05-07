package hwr.oop;

import java.util.Arrays;

public class Player {
    private String name;
    public RoundState[] roundStates;
    public int[] points;
    private int pointCount;

    public Player(int[] points,RoundState[] roundStates) {
        this.points =  new int[10];
        this.roundStates =  new RoundState[10];
    }

    public void setPointCount(int pointCount) {
        if (0 <= pointCount && pointCount <= 300) {
            this.pointCount = pointCount;
            return;
        }
        throw new IllegalArgumentException("New Point Count is too high!");
    }

    public void setName(String name) {
        if (name.length() < 50) {
            this.name = name;
            return;
        }
        throw new IllegalArgumentException("Name too long!");
    }

    public String getName() {
        return name;
    }

    private String checkPhrase;

    public void setCheckPhrase(String checkPhrase) {
        this.checkPhrase = checkPhrase;
    }

    public boolean validateCheckPhrase(String challenge) {
        return this.checkPhrase.equals(challenge);
    }

    private String extraUIData;

    public void setExtraUIData(String extraUIData) {
        this.extraUIData = extraUIData;
    }

    public String getExtraUIData() {
        return extraUIData;
    }


    public void addPoints(int points) {
        if (points > 0 && (points + pointCount) <= 300) {
            this.pointCount += points;
            return;
        }
        throw new IllegalArgumentException("New Point Count is too high after addition!");
    }

    public int getPointCount() {
        return pointCount;
    }

    // TODO: Highscore-Functionality



    public Player(String name, int pointCount) {
        setName(name);
        setPointCount(pointCount);
    }

    public int calculatePoints(Player player){
        for(int round = 0; round<10; round++){
            if(round<9){
                if(round>=2 && player.roundStates[round-2].equals(RoundState.STRIKE) ){
                    player.points[round-2] += player.points[round-1]+player.points[round];
                }
                if(round>=1 && player.roundStates[round-1].equals(RoundState.SPARE) ){
                    player.points[round-1] += player.points[round];
                }
            }
        }
        return Arrays.stream(player.points).sum();
    }
}
