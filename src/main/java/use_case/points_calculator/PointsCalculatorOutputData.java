package use_case.points_calculator;

/**
 * The output data for PointsCalculator use case
 */
public class PointsCalculatorOutputData {
    private final int points;
    private final String message;

    public PointsCalculatorOutputData(int points, String message) {
        this.points = points;
        this.message = message;
    }
    public int getPoints() {return points;}
    public String getMessage() {return message;}

}
