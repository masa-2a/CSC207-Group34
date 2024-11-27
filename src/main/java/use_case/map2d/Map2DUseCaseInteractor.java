package use_case.map2d;

import entity.Map2D;

public class Map2DUseCaseInteractor implements Map2DInputBoundary{

    private final Map2D map2D;

    public Map2DUseCaseInteractor() {
        this.map2D = new Map2D();
    }

    @Override
    public Map2DOutputData execute(Map2DInputData map2DInputData) {
        int width = map2DInputData.getWidth();
        int height = map2DInputData.getHeight();
        int zoom = map2DInputData.getZoom();
        double latitude = map2DInputData.getLatitude();
        double longitude = map2DInputData.getLongitude();
        double guessLat = map2DInputData.getGuessLat();
        double guessLong = map2DInputData.getGuessLong();
        double answerLat = map2DInputData.getAnswerLat();
        double answerLong = map2DInputData.getAnswerLong();
        boolean guessed = map2DInputData.isGuessed();
        boolean answered = map2DInputData.isAnswered();


        map2D.createMap(width, height, latitude, longitude,
                zoom, guessLat, guessLong, answerLat, answerLong, guessed, answered);
        String path = map2D.saveMap();
        return new Map2DOutputData(path);
    }

}





