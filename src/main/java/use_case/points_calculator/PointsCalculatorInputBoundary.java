package use_case.points_calculator;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface PointsCalculatorInputBoundary {

    /**
     * Executes the points calculator use case.
     * @param pointsCalculatorInputData the input data
     */
    double execute(PointsCalculatorInputData pointsCalculatorInputData);
}

