package use_case.hint;


import use_case.hint.HintOutputData;

public interface HintOutputBoundary {
    /**
     * Prepares the success view for the PointsCalculator Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(HintOutputData outputData);

}
