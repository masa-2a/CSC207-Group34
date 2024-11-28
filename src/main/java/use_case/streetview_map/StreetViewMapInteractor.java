package use_case.streetview_map;

import entity.Map;
import javafx.stage.Stage;

public class StreetViewMapInteractor implements StreetViewMapInputBoundary {
    private final Map map;

    public StreetViewMapInteractor() {
        this.map = new Map();
    }

    @Override
    public StreetViewMapOutputData execute(StreetViewMapInputData streetViewInputData) {

        final double goalLatitude = streetViewInputData.getGoalLatitude();
        final double goalLongitude = streetViewInputData.getGoalLongitude();

        Stage stage = new Stage();

        map.giveCoords(goalLatitude, goalLongitude);
        map.start(stage);

        double userLatitude = map.getUserLatitude();
        double userLongitude = map.getUserLongitude();

        final StreetViewMapOutputData streetViewMapOutputData = new
                StreetViewMapOutputData(userLatitude, userLongitude);
        return streetViewMapOutputData;
    }
}
