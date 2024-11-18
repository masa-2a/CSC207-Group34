package use_case.points_calculator;

import use_case.login.LoginOutputData;

public interface PointsCalculatorOutputBoundary {
    /**
     * Prepares the success view for the PointsCalculator Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(LoginOutputData outputData);
}
