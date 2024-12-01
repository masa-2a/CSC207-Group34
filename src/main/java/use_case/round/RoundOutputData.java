package use_case.round;

import java.util.Map;

/**
 * Output Data for the Round Use Case.
 */
public class RoundOutputData {
    private final Map<String, Double> randomLocation;
    private final Map<String, Double> chosenLocation;
    private final String country;
    private final double timespent;
    private int hintsused;
    private final String imagepath;

    public RoundOutputData(Map<String, Double> randomLocation, Map<String, Double> chosenLocation,
                            String country, double timespent, int hintsused, String imagepath) {
        this.randomLocation = randomLocation;
        this.chosenLocation = chosenLocation;
        this.country = country;
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

    public void setHintsused(int hintsused) {
        this.hintsused = hintsused;
    }

    public String getImagepath() {
        return imagepath;
    }

    public String getCountry() {
        return country;
    }
}
