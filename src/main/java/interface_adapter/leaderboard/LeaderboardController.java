package interface_adapter.leaderboard;

import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInputData;

/**
 * Controller for leaderboard Use Case.
 */
public class LeaderboardController {
    private final LeaderboardInputBoundary leaderboardInteractor;

    /**
     * Creates LeaderboardController instance.
     * @param leaderboardInputBoundary the leaderboard input boundary
     */
    public LeaderboardController(
            LeaderboardInputBoundary leaderboardInputBoundary) {
        this.leaderboardInteractor = leaderboardInputBoundary;
    }

    /**
     * Executes the leaderboard Usecase.
     * @param currentUserName current loggedin users name
     */
    public void execute(String currentUserName) {
        final LeaderboardInputData inputData = new LeaderboardInputData(currentUserName);
        leaderboardInteractor.execute(inputData);
    }

    /**
     * Switches to the menu view.
     */
    public void switchToMenuView() {
        leaderboardInteractor.switchToMenuView();
    }
}
