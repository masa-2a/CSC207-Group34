package use_case.streetview_map;

import entity.Map;

public class StreetViewMapInteractor implements StreetViewMapInputBoundary {
    private final StreetViewMapOutputBoundary streetViewPresenter;
    private final Map map;

    public StreetViewMapInteractor(StreetViewMapOutputBoundary streetViewPresenter, Map map) {
        this.streetViewPresenter = streetViewPresenter;
        this.map = map;
    }

    @Override
    public void execute(StreetViewMapInputData streetViewInputData) {

        final double goalLatitude = streetViewInputData.getGoalLatitude();
        final double goalLongitude = streetViewInputData.getGoalLongitude();

        map.loadMap(goalLatitude, goalLongitude);

        double userLatitude = map.getUserLatitude();
        double userLongitude = map.getUserLongitude();

        final StreetViewMapOutputData streetViewMapOutputData = new StreetViewMapOutputData(userLatitude, userLongitude);
        streetViewPresenter.present(streetViewMapOutputData);
    }
}
