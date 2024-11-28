package use_case.hint;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HintInteractor {

    private static Map<String, Map<String, String>> countryData = new HashMap<>();

    static {
        try {
            loadCountryData("country_data.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int hintCount = 0;
    private final List<String> hints;
    private final String country;

    public HintInteractor(HintInputData inputData) {
        this.country = inputData.getCountry();

        // Generate hints based on the input country
        HintOutputData outputData = generateHint(country);
        this.hints = List.of(
                outputData.getYearOfEstablishment(),
                outputData.getOfficialLanguages(),
                outputData.getFlag()
        );
    }

    // Method to get the next hint
    public String getNextHint() {
        if (hintCount < hints.size()) {
            String hint = hints.get(hintCount++);
            return "Hint " + hintCount + ": " + hint;
        } else {
            return "No more hints available.";
        }
    }

    // Generates the hint data
    private HintOutputData generateHint(String country) {
        if (country == null || country.isEmpty()) {
            return new HintOutputData("Unknown", "Unknown", "Unknown");
        }

        // Retrieve data for the given country
        Map<String, String> countryDetails = countryData.getOrDefault(country, new HashMap<>());

        String yearOfEstablishment = countryDetails.getOrDefault("year_of_establishment", "Unknown");
        String officialLanguages = countryDetails.getOrDefault("official_languages", "Unknown");
        String flag = countryDetails.getOrDefault("flag", "Unknown");

        return new HintOutputData(yearOfEstablishment, officialLanguages, flag);
    }

    // Loads the country data from the JSON file
    private static void loadCountryData(String filePath) throws IOException {
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
}