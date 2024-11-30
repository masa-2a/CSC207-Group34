package use_case.round;

import use_case.countdown.CountdownOutputData;
import use_case.hint.HintOutputData;

public interface RoundOutputBoundary {
    /**
     * Presents the Map View.
     */
    void presentMapData(RoundOutputData roundOutputData);

    void switchToPointsCalculator(RoundOutputData roundOutputData);

    void updateCountdownTimer(CountdownOutputData countdownOutputData);

    void updateHints(HintOutputData hint);
}