package interface_adapter.points_calculator;

import java.util.HashMap;
import java.util.Map;

public class PointsCalculatorState {
    private int points;
    public String imagePath;
    private Map<String, Double> randomLocation;
    private Map<String, Double> chosenLocation;
    private double timespent;
    private int hintsused;
    private String imagepath;

    public void updateRandomLocation(Map<String, Double> randomLocation) {
        this.randomLocation = randomLocation;
    }
    public void updateChosenLocation(Map<String, Double> chosenLocation) {
        this.chosenLocation = chosenLocation;
    }
    public void updateTimespent(double timespent) {
        this.timespent = timespent;
    }
    public void updateHintsused(int hintsused) {
        this.hintsused = hintsused;
    }

    public Map<String, Double> getChosenLocation() {
        return chosenLocation;
    }
    public Map<String, Double> getRandomLocation() {
        return randomLocation;
    }
    public double getTimespent() {
        return timespent;
    }
    public int getHintsused() {
        return hintsused;
    }


    /**
     * Getter method
     *
     * @return user points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Getter method
     *
     * @return image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * updates the points with the points earned
     *
     * @param pointsEarned
     */
    public void updatePoints(int pointsEarned) {
        this.points += pointsEarned;
    }

    /**
     * updates the imagePath
     *
     * @param imagePath
     */
    public void updateImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}