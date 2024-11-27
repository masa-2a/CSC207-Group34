package interface_adapter.streetview_map;

import use_case.streetview_map.StreetViewMapInputBoundary;
import use_case.streetview_map.StreetViewMapOutputData;

public class StreetViewMapController {
    private final StreetViewMapInputBoundary interactor;
    private final StreetViewMapPresenter presenter;

    public StreetViewMapController(StreetViewMapInputBoundary interactor, StreetViewMapPresenter presenter) {
        this.interactor = interactor;
        this.presenter = presenter;
    }

    public void printCoordinates(double totalDistance) {
        interactor.printCoordinates(totalDistance);
    }

    public void presentCoordinates(StreetViewMapOutputData outputData) {
        presenter.present(outputData);
    }
}
