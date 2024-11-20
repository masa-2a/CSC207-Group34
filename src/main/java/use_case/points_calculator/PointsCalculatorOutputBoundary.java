package use_case.points_calculator;


import entity.PointsCalculator;

public interface PointsCalculatorOutputBoundary {
    /**
     * Prepares the success view for the PointsCalculator Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(PointsCalculatorOutputData outputData);

}
