package use_case.pointsCalculator;


public interface PointsCalculatorOutputBoundary {
    /**
     * Prepares the success view for the PointsCalculator Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(PointsCalculatorOutputData outputData);

    /**
     * Switches to the Main Menu View.
     */
    void switchToMenuView();
}
