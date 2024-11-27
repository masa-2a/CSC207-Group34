package interface_adapter.streetview_map;

import use_case.streetview_map.StreetViewMapInputBoundary;
import use_case.streetview_map.StreetViewMapOutputData;

public class StreetViewMapViewModel {
    private final StreetViewMapState state;

    public StreetViewMapViewModel() {
        this.state = new StreetViewMapState(0.0, true, "Loading...");
    }

    public StreetViewMapState getState() {
        return state;
    }

    private void updateState(double totalDistance, boolean isLoading, String statusMessage) {
        // Update the state with the new values
        state.setTotalDistance(totalDistance);
        state.setLoading(isLoading);
        state.setStatusMessage(statusMessage);
    }

    public void updateCoordinates(double totalDistance) {
        // This method could be used to handle logic for when coordinates are updated
        updateState(totalDistance, false, "Coordinates Updated");
    }
}
