package use_case.streetview_map;

public class StreetViewMapOutputData {
    private final double totalDistance;

    public StreetViewMapOutputData(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getTotalDistance() {
        return totalDistance;
    }
}
