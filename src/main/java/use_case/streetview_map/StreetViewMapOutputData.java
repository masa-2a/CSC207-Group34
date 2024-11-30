package use_case.streetview_map;

public class StreetViewMapOutputData {
    private final double userLatitude;
    private final double userLongitude;

    public StreetViewMapOutputData(double userLatitude, double userLongitude) {
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
    }

    public double getUserLatitude() {
        return userLatitude;
    }

    public double getUserLongitude() { return userLongitude; }
}
