package use_case.generate_hint;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HintInteractor {

    private int hintCount = 0;
    private List<String> hints; // Stores the three hints in the specified order

    public HintInteractor(String apiKey, String coordinates) {
        // Initialize hints list by generating the hints based on location data
        hints = generateHints(apiKey, coordinates);
    }

    private List<String> generateHints(String apiKey, String coordinates) {
        try {
            String endpoint = "https://api.example.com/data?coordinates=" + coordinates;

            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            // Assume we parse the response to extract country name, etc.
            String country = extractCountryFromResponse(content.toString());

            // Load hints based on the country data
            String establishmentHint = getEstablishmentYearHint(country);
            String languageHint = getOfficialLanguagesHint(country);
            String flagHint = getFlagHint(country);

            return List.of(establishmentHint, languageHint, flagHint);

        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Hint unavailable", "Hint unavailable", "Hint unavailable");
        }
    }

    private String extractCountryFromResponse(String response) {
        // Extract the country name from the response (dummy implementation here)
        return response.substring(response.indexOf("Laos")); // Adjust parsing as necessary
    }

    private String getEstablishmentYearHint(String country) {
        // Fetch establishment year for the country
        return "Year of Establishment: " + CountryDataLoader.loadEstablishmentYears(country);
    }

    private String getOfficialLanguagesHint(String country) {
        // Fetch official languages for the country
        return "Official Languages: " + CountryDataLoader.loadOfficialLanguages(country);
    }

    private String getFlagHint(String country) {
        // Fetch flag or placeholder
        return "Flag: [Flag of " + country + "]";
    }

    public String getNextHint() {
        if (hintCount < hints.size()) {
            String hint = hints.get(hintCount);
            hintCount++; // Increment hint count each time a hint is requested
            return hint;
        } else {
            return "No more hints available.";
        }
    }

    public int getHintCount() {
        return hintCount;
    }
}