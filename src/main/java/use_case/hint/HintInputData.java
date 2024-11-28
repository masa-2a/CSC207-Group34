package use_case.hint;

/**
 * This class contains the input data required for the hint generation.
 */
public class HintInputData {
    private final String country;

    public HintInputData(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

}