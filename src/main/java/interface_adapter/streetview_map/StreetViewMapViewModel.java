package interface_adapter.streetview_map;

import use_case.streetview_map.StreetViewMapInputBoundary;
import use_case.streetview_map.StreetViewMapOutputData;

public class StreetViewMapViewModel {
    private final StreetViewMapState state;
    private final StreetViewMapInputBoundary interactor;

    public StreetViewMapViewModel(StreetViewMapInputBoundary interactor) {
        this.state = new StreetViewMapState(0.0, true, "Loading...");
        this.interactor = interactor;
    }

    public StreetViewMapState getState() {
        return state;
    }

    public void loadMapData() {
        // Simulate a map data loading process
        // This is where you would interact with the interactor and fetch data
        interactor.printCoordinates(100.5);  // Example distance value

        // After the data is fetched, update the state
        updateState(100.5, false, "Map Loaded Successfully");
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
