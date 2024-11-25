package use_case.points_calculator;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface PointsCalculatorInputBoundary {

    /**
     * Executes the points calculator use case.
     * @param pointsCalculatorInputData the input data
     */
    void execute(PointsCalculatorInputData pointsCalculatorInputData);
    /**
     * Executes the switch to Logout view use case.
     */
    void switchToMenuView();
}

