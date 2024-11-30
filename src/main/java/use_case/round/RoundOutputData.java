package use_case.round;

import java.util.Map;

public class RoundOutputData {
    private final Map<String, Double> randomLocation;
    private final Map<String, Double> chosenLocation;
    private final double timespent;
    private final int hintsused;
    private final String imagepath;

    public RoundOutputData (Map<String, Double> randomLocation, Map<String, Double> chosenLocation,
                            double timespent, int hintsused, String imagepath) {
        this.randomLocation = randomLocation;
        this.chosenLocation = chosenLocation;
        this.timespent = timespent;
        this.hintsused = hintsused;
        this.imagepath = imagepath;
    }

    public Map<String, Double> getRandomLocation() {
        return randomLocation;
    }

    public Map<String, Double> getChosenLocation() {
        return chosenLocation;
    }

    public double getTimespent() {
        return timespent;
    }

    public int getHintsused() {
        return hintsused;
    }

    public String getImagepath() {
        return imagepath;
    }
}
