package use_case.round;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class RoundDataAccess implements RoundDataAccessInterface{
    private final String filePath;
    public RoundDataAccess(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Map<String, String>> loadCountryData() {
        Map<String, Map<String, String>> countryData = new HashMap<>();

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

    private static void parseJsonToMap(String json, Map<String, Map<String, String>> countryData) {
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
                String details = parts[1].trim();

                Map<String, String> countryDetails = new HashMap<>();
                details = details.substring(1, details.length() - 1); // Remove inner braces
                String[] attributes = details.split(",");
                for (String attribute : attributes) {
                    String[] keyValue = attribute.split(":");
                    if (keyValue.length == 2) {
                        countryDetails.put(keyValue[0].trim().replaceAll("[{}\"]", ""), keyValue[1].trim().replaceAll("[{}\"]", ""));
                    }
                }

                countryData.put(country, countryDetails);
            }
        }
    }
}
