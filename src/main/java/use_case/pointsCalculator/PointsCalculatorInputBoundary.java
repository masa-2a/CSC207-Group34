package use_case.pointsCalculator;

/**
 * Input Boundary for actions which are related to calculating points.
 */
public interface PointsCalculatorInputBoundary {

    /**
     * Executes the points calculator use case.
     *
     * @param pointsCalculatorInputData the input data
     */
    void execute(PointsCalculatorInputData pointsCalculatorInputData);

    /**
     * Executes the switch to Menu view use case.
     */
    void switchToMenuView();
}

