package use_case.round;

import use_case.streetview_map.StreetViewMapInputData;

/**
 * Input data for the Round Use Case.
 */
public class RoundInputData {
    private final StreetViewMapInputData streetViewMapInputData;
    private final String country;
    private double elapsedTime;
    private int hintsUsed;

    public RoundInputData(double lat, double longitude, String country) {
        this.streetViewMapInputData = new StreetViewMapInputData(lat, longitude);
        this.country = country;
    }

    public StreetViewMapInputData getStreetViewMapInputData() {
        return streetViewMapInputData;
    }

    public String getCountry() {
        return country;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public void setHintsUsed(int hintsUsed) {
        this.hintsUsed = hintsUsed;
    }

    public int getHintsUsed() {
        return hintsUsed;
    }
}
