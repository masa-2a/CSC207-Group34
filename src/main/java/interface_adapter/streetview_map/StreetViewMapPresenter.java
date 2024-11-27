package interface_adapter.streetview_map;

import use_case.streetview_map.StreetViewMapOutputBoundary;
import use_case.streetview_map.StreetViewMapOutputData;

public class StreetViewMapPresenter implements StreetViewMapOutputBoundary {

    public void present(StreetViewMapOutputData outputData) {
        double[] userCoordinates = outputData.getUserCoordinates();
        double[] goalCoordinates = outputData.getGoalCoordinates();

        transferCoordinates(userCoordinates, goalCoordinates);
    }

    private void transferCoordinates(double[] userCoordinates, double[] goalCoordinates) {
        System.out.println(userCoordinates[0] + " " + userCoordinates[1]);
        System.out.println(goalCoordinates[0] + " " + goalCoordinates[1]);
    }
}
