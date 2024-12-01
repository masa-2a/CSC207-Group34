package use_case.pointsCalculator;

import java.util.Map;

/**
 * The Input Data for the Points Calculator Use Case.
 */
public class PointsCalculatorInputData {
    private final Map<String, Double> randomLocation;
    private final Map<String, Double> chosenLocation;
    private final double timespent;
    private final int hintsused;
    private final String imagepath;

    /**
     * The input data for the points calculator use case.
     *
     * @param randomLocation location that is randomly picked by program
     * @param chosenLocation location user picks as their guess
     * @param timespent time taken to make the guess
     * @param hintsused amount of hints used
     * @param imagepath image that shows the diff between randomLocation and chosenLocation
     */
    public PointsCalculatorInputData(Map<String, Double> randomLocation,
                                     Map<String, Double> chosenLocation,
                                     double timespent,
                                     int hintsused,
                                     String imagepath) {
        this.randomLocation = randomLocation;
        this.chosenLocation = chosenLocation;
        this.timespent = timespent;
        this.hintsused = hintsused;
        this.imagepath = imagepath;
    }

    /**
     * Getter method.
     *
     * @return randomLocation
     */
    Map<String, Double> getRandomLocation() {
        return randomLocation;
    }

    /**
     * Getter Method.
     *
     * @return chosenLocation
     */
    Map<String, Double> getChosenLocation() {
        return chosenLocation;
    }

    /**
     * Getter Method.
     *
     * @return Time taken during the round
     */
    double getTimespent() {
        return timespent;
    }

    /**
     * Getter Method.
     *
     * @return the number of hints used
     */
    int getHintsused() {
        return hintsused;
    }

    /**
     * Getter Method.
     *
     * @return the imagepath of the image showing the distance between the guess and the correct answer.
     */
    String getImagepath() {
        return imagepath;
    }
}
