package interface_adapter.map2d;

import use_case.map2d.Map2DInputBoundary;
import use_case.map2d.Map2DInputData;

/**
 * The controller for the Map2D Use Case.
 */
public class Map2DController {
    private final Map2DInputBoundary map2DUseCaseInteractor;

    public Map2DController(Map2DInputBoundary map2DUseCaseInteractor) {
        this.map2DUseCaseInteractor = map2DUseCaseInteractor;
    }

    /**
     * Executes the Map2D Use Case.
     * @param map2DInputData the input data for the use case
     */
    public void execute(Map2DInputData map2DInputData) {
        map2DUseCaseInteractor.execute(map2DInputData);
    }
}
