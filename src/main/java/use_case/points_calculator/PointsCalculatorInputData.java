package use_case.points_calculator;

import java.util.Map;

/**
 * The Input Data for the Points Calculator Use Case.
 */
public class PointsCalculatorInputData {
    private final Map<String, Double> randomLocation;
    private final Map<String, Double> chosenLocation;

    public PointsCalculatorInputData(Map<String, Double> randomLocation, Map<String, Double> chosenLocation) {
        this.randomLocation = randomLocation;
        this.chosenLocation = chosenLocation;
    }

    Map<String, Double> getRandomLocation() {return randomLocation;}
    Map<String, Double> getChosenLocation() {return chosenLocation;}
}
