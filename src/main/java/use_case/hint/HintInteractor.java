package use_case.hint;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HintInteractor implements HintInputBoundary{
    private int hintCount;
    private Map<String, Map<String, String>> countryData;
    private List<String> hints;

    public HintInteractor() {
        this.hintCount = 0;
        this.countryData = new HashMap<>();
    }

    @Override
    public HintOutputData execute(HintInputData hintinputData) {
        String country = hintinputData.getCountry();

        if (countryData.isEmpty()) {
            try {
                loadCountryData(hintinputData.getFilePath());
                // Generate hints based on the input country
                generateHint(country);
                return getNextHint();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getNextHint();
    }

    // Loads the country data from the JSON file
    private void loadCountryData(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        JSONTokener tokener = new JSONTokener(fileInputStream);
        JSONObject jsonObject = new JSONObject(tokener);

        // Populate the country data map
        for (String country : jsonObject.keySet()) {
            JSONObject countryDetails = jsonObject.getJSONObject(country);
            Map<String, String> countryMap = new HashMap<>();
            countryMap.put("year_of_establishment", countryDetails.optString("year_of_establishment", "Unknown"));
            countryMap.put("official_languages", countryDetails.optString("official_languages", "Unknown"));
            countryMap.put("flag", countryDetails.optString("flag", "Unknown"));
            countryData.put(country, countryMap);
        }
    }

    // Generates the hint data
    private void generateHint(String country) {
        // Retrieve data for the given country
        Map<String, String> countryDetails = countryData.getOrDefault(country, new HashMap<>());

        String yearOfEstablishment = countryDetails.getOrDefault("year_of_establishment", "Unknown");
        String officialLanguages = countryDetails.getOrDefault("official_languages", "Unknown");
        String flag = countryDetails.getOrDefault("flag", "Unknown");

        this.hints = List.of(yearOfEstablishment, officialLanguages, flag);
    }

    // Method to get the next hint
    private HintOutputData getNextHint() {
        if (hintCount < hints.size()) {
            String hint = hints.get(hintCount);
            hintCount++;
            String hintFinal = "Hint " + (hintCount) + ": " + hint;
            return new HintOutputData(hintFinal, hintCount);
        } else {
            String hint = "No more hints available.";
            return new HintOutputData(hint,hintCount);
        }
    }

}