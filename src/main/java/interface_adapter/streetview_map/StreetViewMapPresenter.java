package interface_adapter.streetview_map;

import use_case.streetview_map.StreetViewMapOutputBoundary;
import use_case.streetview_map.StreetViewMapOutputData;

/**
 * Presenter for the StreetViewMap Use Case.
 */
public class StreetViewMapPresenter implements StreetViewMapOutputBoundary {

    /**
     * Presents the output data.
     *
     * @param outputData the output data
     */
    public void present(StreetViewMapOutputData outputData) {
        final double userLatitude = outputData.getUserLatitude();
        final double userLongitude = outputData.getUserLongitude();

        transferCoordinates(userLatitude, userLongitude);
    }

    /**
     * Transfers the coordinates to the user.
     * @param userLatitude  the user's guessed latitude
     * @param userLongitude the user's guessed longitude
     */
    private void transferCoordinates(double userLatitude, double userLongitude) {
        System.out.println(userLatitude + " " + userLongitude);
    }
}
