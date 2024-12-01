package use_case.streetview_map;

/**
 * Output Data for the StreetViewMap Use Case.
 */
public class StreetViewMapOutputData {
    private final double userLatitude;
    private final double userLongitude;

    public StreetViewMapOutputData(double userLatitude, double userLongitude) {
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
    }

    /**
     * Gets the user latitude.
     *
     * @return the user latitude
     */
    public double getUserLatitude() {
        return userLatitude;
    }

    /**
     * Gets the user longitude.
     *
     * @return the user longitude
     */
    public double getUserLongitude() {
        return userLongitude;
    }
}
