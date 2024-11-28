package use_case.streetview_map;

import entity.Map;
import javafx.application.Platform;
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

        Platform.runLater(() -> {
            Stage stage = new Stage();
            map.giveCoords(goalLatitude, goalLongitude);
            map.start(stage);
        });

        double userLatitude = 0.0;
        double userLongitude = 0.0;

        return new StreetViewMapOutputData(userLatitude, userLongitude);
    }

}
