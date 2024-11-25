package use_case.streetview_map;

public class StreetViewMapInputData {
    private final double totalDistance;

    // Constructor to initialize the input data
    public StreetViewMapInputData(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    // Getter for totalDistance
    public double getTotalDistance() {
        return totalDistance;
    }
}
