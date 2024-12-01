package use_case.hint;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;

public class HintInteractor implements HintInputBoundary {

    private static final String EST_YEAR = "year_of_establishment";
    private static final String UNKNOWN = "Unknown";
    private static final String FLAG = "flag";
    private static final String OFFICIAL_LANGUAGES = "official_languages";

    private int hintCount;
    private Map<String, Map<String, String>> countryData;
    private List<String> hints;

    public HintInteractor() {
        this.hintCount = 0;
        this.countryData = new HashMap<>();
    }

    @Override
    public HintOutputData execute(HintInputData hintinputData) {
        final String country = hintinputData.getCountry();

        if (countryData.isEmpty()) {
            try {
                loadCountryData(hintinputData.getFilePath());
                // Generate hints based on the input country
                generateHint(country);
                return getNextHint();
            }
            catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return getNextHint();
    }

    // Loads the country data from the JSON file
    private void loadCountryData(String filePath) throws IOException {
        final FileInputStream fileInputStream = new FileInputStream(filePath);
        final JSONTokener tokener = new JSONTokener(fileInputStream);
        final JSONObject jsonObject = new JSONObject(tokener);

        // Populate the country data map
        for (String country : jsonObject.keySet()) {
            final JSONObject countryDetails = jsonObject.getJSONObject(country);
            final Map<String, String> countryMap = new HashMap<>();
            countryMap.put(EST_YEAR, countryDetails.optString(EST_YEAR, UNKNOWN));
            countryMap.put(OFFICIAL_LANGUAGES, countryDetails.optString(OFFICIAL_LANGUAGES, UNKNOWN));
            countryMap.put(FLAG, countryDetails.optString("flag", UNKNOWN));
            countryData.put(country, countryMap);
        }
    }

    // Generates the hint data
    private void generateHint(String country) {
        // Retrieve data for the given country
        final Map<String, String> countryDetails = countryData.getOrDefault(country, new HashMap<>());

        final String yearOfEstablishment = countryDetails.getOrDefault(EST_YEAR, UNKNOWN);
        final String officialLanguages = countryDetails.getOrDefault(OFFICIAL_LANGUAGES, UNKNOWN);
        final String flag = countryDetails.getOrDefault(FLAG, UNKNOWN);

        this.hints = List.of(yearOfEstablishment, officialLanguages, flag);
    }

    // Method to get the next hint
    private HintOutputData getNextHint() {
        if (hintCount < hints.size()) {
            final String hint = hints.get(hintCount);
            hintCount++;
            final String hintFinal = "Hint " + hintCount + ": " + hint;
            return new HintOutputData(hintFinal, hintCount);
        }
        else {
            final String hint = "No more hints available.";
            return new HintOutputData(hint, hintCount);
        }
    }

}