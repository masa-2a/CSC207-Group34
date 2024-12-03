package use_case.hint;

/**
 * Output boundary for actions related to the Hint use case.
 */
public interface HintOutputBoundary {

    /**
     * Prepares the success view with the provided hint data.
     *
     * @param outputData the output data containing hint details
     */
    void prepareSuccessView(HintOutputData outputData);

    /**
     * Prepares the failure view with the provided error message.
     *
     * @param errorMessage the error message to display
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the main menu view after hint use case execution.
     */
    void switchToMenuView();
}
