package use_case.hint;

/**
 * This class contains the input data required for the hint generation.
 */
public class HintInputData {
    private final String country;
    private final String filePath;

    public HintInputData(String country, String filePath) {
        this.country = country;
        this.filePath = filePath;
    }

    public String getCountry() {
        return country;
    }

    public String getFilePath() {
        return filePath;
    }
}
