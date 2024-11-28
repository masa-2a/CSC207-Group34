package interface_adapter.streetview_map;

import use_case.streetview_map.StreetViewMapInputBoundary;
import use_case.streetview_map.StreetViewMapInputData;

public class StreetViewMapController {
    private final StreetViewMapInputBoundary streetViewMapInteractor;

    public StreetViewMapController(StreetViewMapInputBoundary streetViewMapInteractor) {
        this.streetViewMapInteractor = streetViewMapInteractor;
    }

    public void execute(double goalLatitude, double goalLongitude) {
        final StreetViewMapInputData inputData = new StreetViewMapInputData(goalLatitude, goalLongitude);
        streetViewMapInteractor.execute(inputData);
    }

}
