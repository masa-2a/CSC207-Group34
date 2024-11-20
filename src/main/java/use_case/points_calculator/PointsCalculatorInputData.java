package use_case.points_calculator;

import java.util.Map;

/**
 * The Input Data for the Points Calculator Use Case.
 */
public class PointsCalculatorInputData {
    private final Map<String, Double> randomLocation;
    private final Map<String, Double> chosenLocation;
    private final double timespent;
    private final int hintsused;

    public PointsCalculatorInputData(Map<String, Double> randomLocation, Map<String, Double> chosenLocation, double timespent, int hintsused) {
        this.randomLocation = randomLocation;
        this.chosenLocation = chosenLocation;
        this.timespent = timespent;
        this.hintsused = hintsused;
    }

    Map<String, Double> getRandomLocation() {return randomLocation;}
    Map<String, Double> getChosenLocation() {return chosenLocation;}
    double getTimespent() {return timespent;}
    int getHintsused() {return hintsused;}
}
