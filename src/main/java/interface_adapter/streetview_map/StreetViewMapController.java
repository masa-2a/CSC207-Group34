package interface_adapter.streetview_map;

import use_case.streetview_map.StreetViewMapInputBoundary;
import use_case.streetview_map.StreetViewMapInputData;
import use_case.streetview_map.StreetViewMapOutputBoundary;
import use_case.streetview_map.StreetViewMapOutputData;

public class StreetViewMapController {
    private final StreetViewMapInputBoundary interactor;
    private final StreetViewMapOutputBoundary presenter;

    public StreetViewMapController(StreetViewMapInputBoundary interactor, StreetViewMapOutputBoundary presenter) {
        this.interactor = interactor;
        this.presenter = presenter;
    }

    public void execute(double[] userCoordinates, double[] goalCoordinates) {
        final StreetViewMapInputData inputData = new StreetViewMapInputData(userCoordinates, goalCoordinates);
        interactor.execute(inputData);
    }

    public void presentCoordinates(StreetViewMapOutputData outputData) {
        presenter.present(outputData);
    }
}
