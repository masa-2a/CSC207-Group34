package use_case.round;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class RoundDataAccess implements RoundDataAccessInterface{
    private final String filePath;
    public RoundDataAccess(String filePath) {
        this.filePath = filePath;
    }

    private static void parseJsonToMap(String json, Map<String, Map<String, Object>> countryData) {
        json = json.trim();
        json = json.substring(1, json.length() - 1); // Remove outer braces
        String[] countries = json.split("},"); // Split by individual country entries

        for (String countryEntry : countries) {
            if (!countryEntry.endsWith("}")) {
                countryEntry += "}"; // Add closing brace if missing
            }
            String[] parts = countryEntry.split(":", 2);
            if (parts.length == 2) {
                String country = parts[0].trim().replaceAll("[{}\"]", ""); // Country name
                Map<String, Object> countryDetails = getStringObjectMap(parts);

                countryData.put(country, countryDetails);
            }
        }
    }

    @NotNull
    private static Map<String, Object> getStringObjectMap(String[] parts) {
        String details = parts[1].trim();

        Map<String, Object> countryDetails = new HashMap<>();
        details = details.substring(1, details.length() - 1); // Remove inner braces
        String[] attributes = details.split(",");
        for (String attribute : attributes) {
            String[] keyValue = attribute.split(":");
            if (keyValue.length == 2) {
                countryDetails.put(keyValue[0].trim().replaceAll("[{}\"]", ""), keyValue[1].trim().replaceAll("[{}\"]", ""));
            }
        }
        return countryDetails;
    }

    public Map<String, Map<String, Object>> loadCountryData() {
        Map<String, Map<String, Object>> countryData = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                jsonContent.append(line.trim());
            }

            parseJsonToMap(jsonContent.toString(), countryData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return countryData;
    }
}