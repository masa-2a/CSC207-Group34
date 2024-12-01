package use_case.streetview_map;

/**
 * Input Data for the StreetViewMap Use Case.
 */
public class StreetViewMapInputData {

    private final double goalLongitude;
    private final double goalLatitude;

    public StreetViewMapInputData(double goalLongitude, double goalLatitude) {
        this.goalLongitude = goalLongitude;
        this.goalLatitude = goalLatitude;
    }

    /**
     * Gets the goal longitude.
     *
     * @return the goal longitude
     */
    public double getGoalLongitude() {
        return goalLongitude;
    }

    /**
     * Gets the goal latitude.
     *
     * @return the goal latitude
     */
    public double getGoalLatitude() {
        return goalLatitude;
    }
}
