package use_case.round;

import use_case.streetview_map.StreetViewMapInputData;

public class RoundInputData {
    private final StreetViewMapInputData streetViewMapInputData;
    private final String country;
    private double elapsedTime;

    public RoundInputData(double lat, double longitude, String country) {
        this.streetViewMapInputData = new StreetViewMapInputData(longitude, lat);
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
}