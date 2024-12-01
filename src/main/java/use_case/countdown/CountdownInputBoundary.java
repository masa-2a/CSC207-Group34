package use_case.countdown;

/**
 * The interface for the Countdown Use Case.
 */
public interface CountdownInputBoundary {

    /**
     * Starts the countdown.
     *
     * @param countdownInputData the input data for the countdown
     */
    void startCountdown(CountdownInputData countdownInputData);

    /**
     * Stops the countdown.
     *
     * @return the output data for the countdown
     */
    CountdownOutputData stopCountdown();

    /**
     * Calculate remaining time after submit is clicked.
     *
     * @return the output data for remaining time
     */
    double calculateRemainingTimeOnSubmit();
}
