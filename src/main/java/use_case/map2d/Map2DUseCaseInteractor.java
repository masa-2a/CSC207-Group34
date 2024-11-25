package use_case.map2d;

import entity.Map2D;

public class Map2DUseCaseInteractor implements Map2DInputBoundary{

    private final Map2DOutputBoundary map2DPresenter;
    private final Map2D map2D;

    public Map2DUseCaseInteractor(Map2DOutputBoundary map2DPresenter) {
        this.map2DPresenter = map2DPresenter;
        this.map2D = new Map2D();
    }

    @Override
    public void execute(Map2DInputData map2DInputData) {
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
        final Map2DOutputData map2DOutputData = new Map2DOutputData(path);

        map2DPresenter.prepareMapView(map2DOutputData);
    }
//    int width = 600;
//    int height = 400;
//    int zoom = 2;
}





