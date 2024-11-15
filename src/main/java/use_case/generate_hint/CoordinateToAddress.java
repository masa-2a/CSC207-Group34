package use_case.generate_hint;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CoordinateToAddress {
    private static final String API_KEY = "d5189589571542e790ff0f6ce983faa2";
    private static final String BASE_URL = "https://api.example.com/convert"; // Replace with actual API endpoint

    public String getCountryFromCoordinates(double latitude, double longitude) {
        try {
            String urlString = BASE_URL + "?lat=" + latitude + "&lon=" + longitude;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder content = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                conn.disconnect();

                return extractCountry(content.toString());
            } else {
                System.err.println("Error: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String extractCountry(String apiResponse) {
        // Split response by lines to isolate first line containing country info
        String[] lines = apiResponse.split("\n");
        if (lines.length > 0) {
            String firstLine = lines[0].trim(); // e.g., "🇱🇦 unnamed road, Houaphanh, Laos"
            String[] parts = firstLine.split(", "); // Split by commas

            // Check that the format meets expected pattern
            if (parts.length > 1) {
                String countryName = parts[parts.length - 1].trim(); // Last part as country name
                String countryCode = firstLine.substring(0, 2); // First two characters as flag emoji

                return countryName + " (" + countryCode + ")";
            }
        }
        return "Unknown Country";
    }
}