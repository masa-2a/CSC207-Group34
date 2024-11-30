package use_case.streetview_map;

public class StreetViewMapInputData {

    private final double goalLongitude;
    private final double goalLatitude;

    public StreetViewMapInputData(double goalLongitude, double goalLatitude) {
        this.goalLongitude = goalLongitude;
        this.goalLatitude = goalLatitude;
    }

    public double getGoalLongitude() { return goalLongitude; }

    public double getGoalLatitude() { return goalLatitude; }
}
