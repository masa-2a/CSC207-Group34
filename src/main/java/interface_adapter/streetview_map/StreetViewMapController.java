package interface_adapter.streetview_map;

import use_case.streetview_map.StreetViewMapInputBoundary;
import use_case.streetview_map.StreetViewMapInputData;

/**
 * Controller for the StreetViewMap Use Case.
 */
public class StreetViewMapController {
    private final StreetViewMapInputBoundary streetViewMapInteractor;

    /**
     * Constructor for the StreetViewMapController.
     *
     * @param streetViewMapInteractor the StreetViewMapInteractor
     */
    public StreetViewMapController(StreetViewMapInputBoundary streetViewMapInteractor) {
        this.streetViewMapInteractor = streetViewMapInteractor;
    }

    /**
     * Executes the StreetViewMap Use Case.
     *
     * @param goalLatitude  the goal latitude
     * @param goalLongitude the goal longitude
     */
    public void execute(double goalLatitude, double goalLongitude) {
        final StreetViewMapInputData inputData = new StreetViewMapInputData(goalLatitude, goalLongitude);
        streetViewMapInteractor.execute(inputData);
    }

}
