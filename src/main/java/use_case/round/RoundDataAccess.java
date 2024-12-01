package use_case.round;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

import com.google.gson.JsonParseException;

/**
 * Data Access for Round Use Case.
 */
public class RoundDataAccess implements RoundDataAccessInterface {
    private static final String REGEX = "[{}\"]";
    private final String filePath;

    public RoundDataAccess(String filePath) {
        this.filePath = filePath;
    }

    private static void parseJsonToMap(String json, Map<String, Map<String, Object>> countryData) {
        json = json.trim();
        json = json.substring(1, json.length() - 1);
        final String[] countries = json.split("},");

        for (String countryEntry : countries) {
            if (!countryEntry.endsWith("}")) {
                countryEntry += "}";
            }
            final String[] parts = countryEntry.split(":", 2);
            if (parts.length == 2) {
                final String country = parts[0].trim().replaceAll(REGEX, "");
                final Map<String, Object> countryDetails = getStringObjectMap(parts);

                countryData.put(country, countryDetails);
            }
        }
    }

    @NotNull
    private static Map<String, Object> getStringObjectMap(String[] parts) {
        String details = parts[1].trim();

        final Map<String, Object> countryDetails = new HashMap<>();
        details = details.substring(1, details.length() - 1);
        final String[] attributes = details.split(",");
        for (String attribute : attributes) {
            final String[] keyValue = attribute.split(":");
            if (keyValue.length == 2) {
                countryDetails.put(keyValue[0].trim().replaceAll(REGEX, ""),
                        keyValue[1].trim().replaceAll("[{}\"]", ""));
            }
        }
        return countryDetails;
    }

    /**
     * Loads the country data from the file.
     *
     * @return the country data
     */
    public Map<String, Map<String, Object>> loadCountryData() {
        final Map<String, Map<String, Object>> countryData = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            final StringBuilder jsonContent = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                jsonContent.append(line.trim());
            }

            parseJsonToMap(jsonContent.toString(), countryData);

        }
        catch (IOException ioException) {
            System.err.println("An error occurred while reading the file: " + ioException.getMessage());
            ioException.printStackTrace();
        }
        catch (JsonParseException jsonParseException) {
            System.err.println("An error occurred while parsing the JSON: " + jsonParseException.getMessage());
            jsonParseException.printStackTrace();
        }

        return countryData;
    }
}
