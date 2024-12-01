package use_case.round;

import use_case.countdown.CountdownOutputData;
import use_case.hint.HintOutputData;

/**
 * Output Boundary for the Round Use Case.
 */
public interface RoundOutputBoundary {
    /**
     * Presents the Map View.
     * @param roundOutputData the output data
     */
    void presentMapData(RoundOutputData roundOutputData);

    /**
     * Switches to the Points Calculator.
     * @param roundOutputData the output data
     */
    void switchToPointsCalculator(RoundOutputData roundOutputData);

    /**
     * Updates the Countdown Timer.
     * @param countdownOutputData the output data
     */
    void updateCountdownTimer(CountdownOutputData countdownOutputData);

    /**
     * Updates the Hints.
     * @param hint the hint
     */
    void updateHints(HintOutputData hint);
}
