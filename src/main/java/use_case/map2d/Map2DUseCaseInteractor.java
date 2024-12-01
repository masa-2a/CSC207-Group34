package use_case.map2d;

import entity.map.Map2D;

/**
 * Use Case Interactor for the Map2D Use Case.
 */
public class Map2DUseCaseInteractor implements Map2DInputBoundary {

    private final Map2D map2D;

    public Map2DUseCaseInteractor() {
        this.map2D = new Map2D();
    }

    @Override
    public Map2DOutputData execute(Map2DInputData map2DInputData) {
        final int width = map2DInputData.getWidth();
        final int height = map2DInputData.getHeight();
        final int zoom = map2DInputData.getZoom();
        final double latitude = map2DInputData.getLatitude();
        final double longitude = map2DInputData.getLongitude();
        final double guessLat = map2DInputData.getGuessLat();
        final double guessLong = map2DInputData.getGuessLong();
        final double answerLat = map2DInputData.getAnswerLat();
        final double answerLong = map2DInputData.getAnswerLong();
        final boolean guessed = map2DInputData.isGuessed();
        final boolean answered = map2DInputData.isAnswered();

        map2D.createMap(width, height, latitude, longitude,
                zoom, guessLat, guessLong, answerLat, answerLong, guessed, answered);
        final String path = map2D.saveMap();
        return new Map2DOutputData(path);
    }
}
