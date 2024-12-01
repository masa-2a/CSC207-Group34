package use_case.streetview_map;

import entity.map.Map;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Interactor for the StreetViewMap Use Case.
 */
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
            final Stage stage = new Stage();
            map.giveCoords(goalLatitude, goalLongitude);
            map.start(stage);
        });
    }

    @Override
    public StreetViewMapOutputData guessSubmit() {
        return new StreetViewMapOutputData(map.getUserLatitude(), map.getUserLongitude());
    }

}
