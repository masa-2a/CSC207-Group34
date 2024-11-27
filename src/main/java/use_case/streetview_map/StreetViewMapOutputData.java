package use_case.streetview_map;

public class StreetViewMapOutputData {
    private final double[] userCoordinates;
    private final double[] goalCoordinates;

    public StreetViewMapOutputData(double[] userCoordinates, double[] goalCoordinates) {
        this.userCoordinates = userCoordinates;
        this.goalCoordinates = goalCoordinates;
    }

    public double[] getUserCoordinates() {
        return userCoordinates;
    }

    public double[] getGoalCoordinates() { return goalCoordinates; }
}
