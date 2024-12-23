package use_case.hint;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface HintInputBoundary {

    /**
     * Executes the hint use case.
     * @param hintinputData the input data
     * @return HintOutputData a string with the hint.
     */
    HintOutputData execute(HintInputData hintinputData);
}
