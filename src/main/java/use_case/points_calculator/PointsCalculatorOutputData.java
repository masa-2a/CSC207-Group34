package use_case.points_calculator;

/**
 * The output data for PointsCalculator use case
 */
public class PointsCalculatorOutputData {
    private final int points;
    private final String message;
    private final String imagepath;

    public PointsCalculatorOutputData(int points, String message, String imagepath) {
        this.points = points;
        this.message = message;
        this.imagepath = imagepath;
    }
    public int getPoints() {return points;}
    public String getMessage() {return message;}
    public String getImagepath() {return imagepath;}
}
