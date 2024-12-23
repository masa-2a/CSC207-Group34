package use_case.streetview_map;

import entity.map.Map;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

/**
 * Interactor for the StreetViewMap Use Case.
 */
public class StreetViewMapInteractor implements StreetViewMapInputBoundary {
    private final Map map;
    private final PlatformRunner platformRunner;
    private Stage stage;

    /**
     * Constructor using default Platform.runLater.
     */
    public StreetViewMapInteractor() {
        this.map = new Map();
        this.platformRunner = Platform::runLater;
    }

    /**
     * Constructor for testing to inject custom PlatformRunner.
     *
     * @param map             The map instance to use.
     * @param platformRunner  A custom runner to replace Platform.runLater.
     */
    public StreetViewMapInteractor(Map map, PlatformRunner platformRunner) {
        this.map = map;
        this.platformRunner = platformRunner;
    }

    @Override
    public void execute(StreetViewMapInputData streetViewInputData) {

        // Ensure JavaFX platform is initialized if it hasn't been already
        if (!Platform.isFxApplicationThread()) {
            new JFXPanel();
            // Force initialization of JavaFX
        }

        final double goalLatitude = streetViewInputData.getGoalLatitude();
        final double goalLongitude = streetViewInputData.getGoalLongitude();

        platformRunner.run(() -> {
            System.out.println("JavaFX initialized");
            if (stage == null || !stage.isShowing()) {
                stage = new Stage();
            }
            map.giveCoords(goalLatitude, goalLongitude);
            map.start(stage);
        });
    }

    @Override
    public StreetViewMapOutputData guessSubmit() {
        return new StreetViewMapOutputData(map.getUserLatitude(), map.getUserLongitude());
    }

    /**
     * Functional interface for running tasks, allowing replacement of Platform.runLater.
     */
    @FunctionalInterface
    public interface PlatformRunner {
        /**
         * Runs the given Runnable on the JavaFX application thread.
         *
         * @param runnable The Runnable to run.
         */
        void run(Runnable runnable);
    }
}
