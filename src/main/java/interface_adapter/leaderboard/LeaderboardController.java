package interface_adapter.leaderboard;

import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInputData;


/**
 * Controller for leaderboard Use Case
 */
public class LeaderboardController {
    private final LeaderboardInputBoundary leaderboardInteractor;

    public LeaderboardController(LeaderboardInputBoundary leaderboardInputBoundary) {
        this.leaderboardInteractor = leaderboardInputBoundary;
    }

    /**
     * Executes the leaderboard Usecase
     * @param currentUserName current loggedin users name
     */
    public void execute(String currentUserName) {
        System.out.println("Controller executed leaderboard");
        LeaderboardInputData inputData = new LeaderboardInputData(currentUserName);
        leaderboardInteractor.execute(inputData);
    }

    public void switchToMenuView() {
        leaderboardInteractor.switchToMenuView();
    }
}
