package interface_adapter.streetview_map;

import use_case.streetview_map.StreetViewMapOutputBoundary;
import use_case.streetview_map.StreetViewMapOutputData;

public class StreetViewMapPresenter implements StreetViewMapOutputBoundary {

    public void present(StreetViewMapOutputData outputData) {
        double userLatitude = outputData.getUserLatitude();
        double userLongitude = outputData.getUserLongitude();

        transferCoordinates(userLatitude, userLongitude);
    }

    private void transferCoordinates(double userLatitude, double userLongitude) {
        System.out.println(userLatitude + " " + userLongitude);
    }
}
