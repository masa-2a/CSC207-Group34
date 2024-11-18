package use_case.hint;

/**
 * This class contains the input data required for the hint generation.
 */
public class HintInputData {
    private final double latitude;
    private final double longitude;

    public HintInputData(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}