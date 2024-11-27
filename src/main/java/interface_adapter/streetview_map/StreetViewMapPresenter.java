package interface_adapter.streetview_map;

import use_case.streetview_map.StreetViewMapOutputData;

public class StreetViewMapPresenter {

    public void present(StreetViewMapOutputData outputData) {
        double formattedDistance = formatDistance(outputData.getTotalDistance());

        updateView(formattedDistance);
    }

    private double formatDistance(double distance) {
        // Example of rounding the distance to two decimal places for display
        return Math.round(distance * 100.0) / 100.0;
    }

    private void updateView(double formattedDistance) {
        // Here you could update the JavaFX UI (or other UI frameworks) with the processed data
        System.out.println("Formatted Total Distance: " + formattedDistance + " km");
    }
}
