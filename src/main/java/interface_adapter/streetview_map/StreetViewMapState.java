package interface_adapter.streetview_map;

public class StreetViewMapState {
    private double totalDistance;  // The total distance, which could be displayed on the map
    private boolean isLoading;     // Whether the map is still loading or not
    private String statusMessage;  // A message to indicate the status (e.g., loading, error, etc.)

    public StreetViewMapState(double totalDistance, boolean isLoading, String statusMessage) {
        this.totalDistance = totalDistance;
        this.isLoading = isLoading;
        this.statusMessage = statusMessage;
    }

    // Getters and Setters
    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
