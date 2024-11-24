package use_case.points_calculator;

/**
 * Input Boundary for actions which are related to calculating points
 */
public interface PointsCalculatorInputBoundary {

    /**
     * Executes the points calculator use case.
     * @param pointsCalculatorInputData the input data
     */
    void execute(PointsCalculatorInputData pointsCalculatorInputData);
}

