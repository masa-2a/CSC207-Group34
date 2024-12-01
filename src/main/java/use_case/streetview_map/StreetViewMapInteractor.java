package use_case.streetview_map;

import entity.Map.Map;
import javafx.application.Platform;
import javafx.stage.Stage;

public class StreetViewMapInteractor implements StreetViewMapInputBoundary {
    private final Map map;

    public StreetViewMapInteractor() {
        this.map = new Map();
    }

    @Override
    public void execute(StreetViewMapInputData streetViewInputData) {
        final double goalLatitude = streetViewInputData.getGoalLatitude();
        final double goalLongitude = streetViewInputData.getGoalLongitude();

        Platform.runLater(() -> {
            Stage stage = new Stage();
            map.giveCoords(goalLatitude, goalLongitude);
            map.start(stage);
        });
    }

    @Override
    public StreetViewMapOutputData guessSubmit() {
        return new StreetViewMapOutputData(map.getUserLatitude(),map.getUserLongitude());
    }

}
