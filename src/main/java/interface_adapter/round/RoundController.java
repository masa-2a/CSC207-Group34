package interface_adapter.round;

import use_case.round.RoundInputBoundary;
import use_case.round.RoundInputData;

public class RoundController {

    private final RoundInputBoundary roundUseCaseInteractor;

    public RoundController(RoundInputBoundary roundUseCaseInteractor) {
        this.roundUseCaseInteractor = roundUseCaseInteractor;
    }

    /**
     * Executes the Menu Use Case.
     */
    public void execute() {
        final RoundInputData roundInputData = new RoundInputData();

        roundUseCaseInteractor.execute(roundInputData);
    }
    /**
     * Executes the "switch to LogoutView" Use Case.
     */
    public void switchToLogoutView() {
    }
    /**
     * Executes the "switch to Leaderboard" Use Case.
     */
    public void switchToLeaderboardView() {
    }
    /**
     * Executes the "switch to New Round" Use Case.
     */
    public void switchToNewRoundView() {
    }

}
