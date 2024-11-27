package use_case.hint;

import use_case.hint.HintInputData;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface HintInputBoundary {

    /**
     * Executes the hint use case.
     * @param inputData the input data
     */
    void execute(HintInputData inputData);
}

