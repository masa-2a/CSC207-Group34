package use_case.hint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class HintCountryDataLoader {

    public static Map<String, Map<String, String>> loadCountryData(String filePath) {
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
        json = json.trim().replaceAll("[{}\"]", ""); // Remove braces and quotes
        String[] countries = json.split("},"); // Split by individual country entries

        for (String countryEntry : countries) {
            String[] parts = countryEntry.split(":", 2);
            if (parts.length == 2) {
                String country = parts[0].trim(); // Country name
                String details = parts[1].trim().replaceAll("[{}]", ""); // Inner details

                Map<String, String> countryDetails = new HashMap<>();
                String[] attributes = details.split(",");
                for (String attribute : attributes) {
                    String[] keyValue = attribute.split(":");
                    if (keyValue.length == 2) {
                        countryDetails.put(keyValue[0].trim(), keyValue[1].trim());
                    }
                }

                countryData.put(country, countryDetails);
            }
        }
    }
}