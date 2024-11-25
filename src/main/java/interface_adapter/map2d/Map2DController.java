package interface_adapter.map2d;

import use_case.map2d.Map2DInputBoundary;
import use_case.map2d.Map2DInputData;

public class Map2DController {
    private final Map2DInputBoundary map2DUseCaseInteractor;

    public Map2DController(Map2DInputBoundary map2DUseCaseInteractor) {
        this.map2DUseCaseInteractor = map2DUseCaseInteractor;
    }

    public void execute(int width, int height, double latitude, double longitude,
                        int zoom, double guessLat, double guessLong, double answerLat,
                        double answerLong, boolean guessed, boolean answered) {

        final Map2DInputData map2DInputData = new Map2DInputData(width, height, latitude, longitude,
        zoom, guessLat, guessLong, answerLat, answerLong, guessed, answered);

        map2DUseCaseInteractor.execute(map2DInputData);
    }
}
