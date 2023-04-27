package hwr.oop;

public class Player {
    private String name;

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

    private int pointCount;

    public void setPointCount(int pointCount) {
        if (0 < pointCount && pointCount <= 300) {
            this.pointCount = pointCount;
            return;
        }
        throw new IllegalArgumentException("New Point Count is too high!");
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

    private RoundState[] roundStates;

    public Player(String name, int pointCount) {
        setName(name);
        setPointCount(pointCount);
    }
}
