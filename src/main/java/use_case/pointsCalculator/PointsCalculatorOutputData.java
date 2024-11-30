package use_case.pointsCalculator;

/**
 * The output data for PointsCalculator use case.
 */
public class PointsCalculatorOutputData {
    private final int pointsEarned;
    private final String message;
    private final String imagePath;

    /**
     * Output Data for Points Calculator Use Case.
     *
     * @param points the points earned
     * @param message the message stating how many points were earned
     * @param imagePath the path for the image showing the distance between the points.
     */
    public PointsCalculatorOutputData(int points, String message, String imagePath) {
        this.pointsEarned = points;
        this.message = message;
        this.imagePath = imagePath;
    }

    /**
     * Getter Method.
     *
     * @return the points earned in this round
     */
    public int getPointsEarned() {
        return pointsEarned;
    }

    /**
     * Getter Method.
     *
     * @return the output message that states how many points you earned
     */
    public String getMessage() {
        return message;
    }

    /**
     * Getter Method.
     *
     * @return the image path of the image that shows the distance between your
     *          guess (chosenLocation) and the answer (randomLocation).
     */
    public String getImagePath() {
        return imagePath;
    }
}
