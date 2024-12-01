package interface_adapter.points_calculator;

import java.util.Map;

import use_case.pointsCalculator.PointsCalculatorInputBoundary;
import use_case.pointsCalculator.PointsCalculatorInputData;

/**
 * Controller for the Points Calculator Use Case.
 */
public class PointsCalculatorController {
    private final PointsCalculatorInputBoundary pointsUseCaseInteractor;

    /**
     * Controller for Points Use Case.
     *
     * @param pointsUseCaseInteractor the Points Use Case Interactor.
     */
    public PointsCalculatorController(PointsCalculatorInputBoundary pointsUseCaseInteractor) {
        this.pointsUseCaseInteractor = pointsUseCaseInteractor;
    }

    /**
     * Executes the Points Calculator Use Case.
     *
     * @param randomLocation the location the user is trying to guess.
     * @param chosenLocation the location of the user's guess.
     * @param timespent the time spent on the round.
     * @param hintsused the number of hints used.
     * @param imagepath the path to the image of the location.
     */
    public void execute(Map<String, Double> randomLocation,
                        Map<String, Double> chosenLocation,
                        double timespent,
                        int hintsused,
                        String imagepath) {
        final PointsCalculatorInputData pointsCalculatorInputData =
                new PointsCalculatorInputData(randomLocation, chosenLocation, timespent, hintsused, imagepath);

        System.out.println("Points Controller executed");
        pointsUseCaseInteractor.execute(pointsCalculatorInputData);
    }

    /**
     * Executes the "switch to Menu View" Use Case.
     */
    public void switchToMenuView() {
        pointsUseCaseInteractor.switchToMenuView();
    }
}
