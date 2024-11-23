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

        map2D.createMap(width,height,latitude,longitude,zoom);
        String path = map2D.saveMap();

        final Map2DOutputData map2DOutputData = new Map2DOutputData(path);
        map2DPresenter.prepareSuccessView(map2DOutputData);
    }
//    int width = 600;
//    int height = 400;
//    int zoom = 2;
}





