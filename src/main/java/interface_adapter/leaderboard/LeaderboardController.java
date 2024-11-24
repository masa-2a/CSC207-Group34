package interface_adapter.leaderboard;

import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInputData;

/**
 * Controller for leaderboard Use Case
 */
public class LeaderboardController {
    private final LeaderboardInputBoundary leaderboardInputBoundary;

    public LeaderboardController(LeaderboardInputBoundary leaderboardInputBoundary) {
        this.leaderboardInputBoundary = leaderboardInputBoundary;
    }

    /**
     * Executes the leaderboard Usecase
     * @param currentUserName current loggedin users name
     */
    public void execute(String currentUserName) {
        final LeaderboardInputData leaderboardInputData = new LeaderboardInputData(currentUserName);

        leaderboardInputBoundary.execute(leaderboardInputData);
    }

    public void switchToMenuView() {

    }
}
