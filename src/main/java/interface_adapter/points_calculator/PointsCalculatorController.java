package interface_adapter.points_calculator;

import use_case.points_calculator.PointsCalculatorInputBoundary;
import use_case.points_calculator.PointsCalculatorInputData;

import java.util.Map;

public class PointsCalculatorController {
    private final PointsCalculatorInputBoundary pointsUseCaseInteractor;

    public PointsCalculatorController(PointsCalculatorInputBoundary pointsUseCaseInteractor) {
        this.pointsUseCaseInteractor = pointsUseCaseInteractor;
    }

    /**
     * Executes the Points Calculator Use Case.
     * @param randomLocation the location the user is trying to guess.
     * @param chosenLocation the location of the user's guess.
     */
    public void execute(Map<String, Double> randomLocation, Map<String, Double> chosenLocation, double timespent, int hintsused) {
        final PointsCalculatorInputData pointsCalculatorInputData =
                new PointsCalculatorInputData(randomLocation, chosenLocation, timespent, hintsused);

        pointsUseCaseInteractor.execute(pointsCalculatorInputData);
    }

}
