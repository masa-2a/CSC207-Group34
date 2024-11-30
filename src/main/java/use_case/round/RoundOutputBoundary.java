package use_case.round;

import use_case.countdown.CountdownOutputData;

public interface RoundOutputBoundary {
    /**
     * Presents the Map View.
     */
    void presentMapData(RoundOutputData roundOutputData);

    void switchToPointsCalculator(RoundOutputData roundOutputData);

    void updateCountdownTimer(CountdownOutputData countdownOutputData);
}