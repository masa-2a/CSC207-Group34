package use_case.streetview_map;

import entity.Map;

public class StreetViewMapInteractor implements StreetViewMapInputBoundary {
    private final Map map;

    public StreetViewMapInteractor() {
        this.map = new Map();
    }

    @Override
    public StreetViewMapOutputData execute(StreetViewMapInputData streetViewInputData) {

        final double goalLatitude = streetViewInputData.getGoalLatitude();
        final double goalLongitude = streetViewInputData.getGoalLongitude();

        map.loadMap(goalLatitude, goalLongitude);

        double userLatitude = map.getUserLatitude();
        double userLongitude = map.getUserLongitude();

        final StreetViewMapOutputData streetViewMapOutputData = new
                StreetViewMapOutputData(userLatitude, userLongitude);
        return streetViewMapOutputData;
    }
}
