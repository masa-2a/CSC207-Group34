package interface_adapter.round;

/**
 * ViewModel for the Round Use Case.
 */
public class RoundState {
    private String viewName;
    private String mapImagePath;
    private String timeLeft;
    private String username;
    private double goalLatitude;
    private double goalLongitude;
    private double guessedLatitude;
    private double guessedLongitude;
    private String country;
    private String hint;
    private int hintsUsed;

    public RoundState(String viewName) {
        this.setViewName(viewName);
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getMapImagePath() {
        return mapImagePath;
    }

    public void setMapImagePath(String mapImagePath) {
        this.mapImagePath = mapImagePath;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getGoalLatitude() {
        return goalLatitude;
    }

    public void setGoalLatitude(double goalLatitude) {
        this.goalLatitude = goalLatitude;
    }

    public double getGoalLongitude() {
        return goalLongitude;
    }

    public void setGoalLongitude(double goalLongitude) {
        this.goalLongitude = goalLongitude;
    }

    public double getGuessedLatitude() {
        return guessedLatitude;
    }

    public void setGuessedLatitude(double guessedLatitude) {
        this.guessedLatitude = guessedLatitude;
    }

    public double getGuessedLongitude() {
        return guessedLongitude;
    }

    public void setGuessedLongitude(double guessedLongitude) {
        this.guessedLongitude = guessedLongitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getHintsUsed() {
        return hintsUsed;
    }

    public void setHintsUsed(int hintsUsed) {
        this.hintsUsed = hintsUsed;
    }

    /**
     * Increment the number of hints used.
     */
    public void incrementHintsUsed() {
        this.hintsUsed++;
    }
}
