package interface_adapter.points_calculator;

import java.util.Map;

/**
 * State for the Points Calculator Use Case.
 */
public class PointsCalculatorState {
    private int points;
    private String message;
    private String imagePath;
    private Map<String, Double> randomLocation;
    private Map<String, Double> chosenLocation;

    private double timespent;
    private int hintsused;
    private String imagepath;

    /**
     * Setter method for the random location.
     * @param randLocation the random location
     */
    public void updateRandomLocation(Map<String, Double> randLocation) {
        this.randomLocation = randLocation;
    }

    /**
     * Setter method for the chosen location.
     * @param selectedLocation the chosen location
     */
    public void updateChosenLocation(Map<String, Double> selectedLocation) {
        this.chosenLocation = selectedLocation;
    }

    /**
     * Setter method for the time spent.
     * @param time the time spent
     */
    public void updateTimespent(double time) {
        this.timespent = time;
    }

    /**
     * Setter method for the hints used.
     * @param hints the hints used
     */
    public void updateHintsused(int hints) {
        this.hintsused = hints;
    }

    /**
     * Setter method for the message.
     * @param message the image path
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter method for the message.
     * @return message of the user
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for the user's points.
     * @param points the number of points the user has
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Getter method for the user's points.
     * @return user points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Getter method for the random location.
     * @return image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Updates the points with the points earned.
     *
     * @param pointsEarned the points earned
     */
    public void updatePoints(int pointsEarned) {
        this.points += pointsEarned;
    }

    /**
     * Updates the imagePath.
     *
     * @param imgPath the image path
     */
    public void updateImagePath(String imgPath) {
        this.imagePath = imgPath;
    }

}
