package interface_adapter.leaderboard;

public class LeaderboardState {
    private String viewName;
    private int currentUserAvgPoints;
    private String firstPlaceName;
    private String secondPlaceName;
    private String thirdPlaceName;
    private String currentUsername;
    private String currentUserPlace;
    private int currentUserPoints;
    private int firstPlacePoints;
    private int secondPlacePoints;
    private int thirdPlacePoints;

    public LeaderboardState(String viewName) {
        this.setViewName(viewName);
    }

    public void setCurrentUserName(String currentUsername) {
        this.setCurrentUsername(currentUsername);
    }

    public void setCurrentUserAvgPoints(int currentUserAvgPoints) {
        this.currentUserAvgPoints = currentUserAvgPoints;
    }


    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }


    public int getCurrentUserAvgPoints() {
        return currentUserAvgPoints;
    }

    public String getFirstPlaceName() {
        return firstPlaceName;
    }

    public void setFirstPlaceName(String firstPlaceName) {
        this.firstPlaceName = firstPlaceName;
    }

    public String getSecondPlaceName() {
        return secondPlaceName;
    }

    public void setSecondPlaceName(String secondPlaceName) {
        this.secondPlaceName = secondPlaceName;
    }

    public String getThirdPlaceName() {
        return thirdPlaceName;
    }

    public void setThirdPlaceName(String thirdPlaceName) {
        this.thirdPlaceName = thirdPlaceName;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public String getCurrentUserPlace() {
        return currentUserPlace;
    }

    public void setCurrentUserPlace(String currentUserPlace) {
        this.currentUserPlace = currentUserPlace;
    }

    public int getCurrentUserPoints() {
        return currentUserPoints;
    }

    public void setCurrentUserPoints(int currentUserPoints) {
        this.currentUserPoints = currentUserPoints;
    }

    public int getFirstPlacePoints() {
        return firstPlacePoints;
    }

    public void setFirstPlacePoints(int firstPlacePoints) {
        this.firstPlacePoints = firstPlacePoints;
    }

    public int getSecondPlacePoints() {
        return secondPlacePoints;
    }

    public void setSecondPlacePoints(int secondPlacePoints) {
        this.secondPlacePoints = secondPlacePoints;
    }

    public int getThirdPlacePoints() {
        return thirdPlacePoints;
    }

    public void setThirdPlacePoints(int thirdPlacePoints) {
        this.thirdPlacePoints = thirdPlacePoints;
    }
}
