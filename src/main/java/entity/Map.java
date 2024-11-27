package entity;

public class Map {
    private final double[] userCoordinates;
    private final double[] goalCoordinates;

    public Map(double[] userCoordinates, double[] goalCoordinates) {
        this.userCoordinates = userCoordinates;
        this.goalCoordinates = goalCoordinates;
    }

    public double[] getUserCoordinates() { return userCoordinates; }

    public double[] getGoalCoordinates() { return goalCoordinates; }

}
