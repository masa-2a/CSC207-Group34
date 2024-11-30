package geocoding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CoordinatesToCountry {

    private static final String API_KEY = "d5189589571542e790ff0f6ce983faa2";
    private static final String BASE_URL = "https://api.opencagedata.com/geocode/v1/json";

    public String getCountryFromCoordinates(double latitude, double longitude) {
        try {
            String urlString = BASE_URL + "?q=" + latitude + "," + longitude + "&key=" + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder content = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine).append("\n");
                }
                in.close();
                conn.disconnect();

                return parseApiResult(content.toString());
            } else {
                System.err.println("Error: " + responseCode);
                return "Unknown Country";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown Country";
        }
    }

    private String parseApiResult(String apiResult) {
        String[] lines = apiResult.split("\n");

        if (lines.length > 0) {
            // First line contains the flag and country
            String firstLine = lines[0].trim(); // e.g., "🇩🇪 Design Offices, Philipsbornstraße 2, 30165 Hanover, Germany"
            String[] parts = firstLine.split(", ");
            if (parts.length > 1) {
                String flagAndCountry = parts[parts.length - 1]; // Extract country and flag
                return flagAndCountry; // Returns e.g., "Germany"
            }
        }
        return "Unknown Country";
    }
}