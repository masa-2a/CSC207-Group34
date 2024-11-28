package interface_adapter.map2d;

import use_case.map2d.Map2DInputBoundary;
import use_case.map2d.Map2DInputData;

public class Map2DController {
    private final Map2DInputBoundary map2DUseCaseInteractor;

    public Map2DController(Map2DInputBoundary map2DUseCaseInteractor) {
        this.map2DUseCaseInteractor = map2DUseCaseInteractor;
    }

    public void execute(Map2DInputData map2DInputData) {

        map2DUseCaseInteractor.execute(map2DInputData);
    }
}
