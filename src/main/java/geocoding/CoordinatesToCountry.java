package geocoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A class that uses the OpenCage Geocoding API to get the country of a given set of coordinates.
 */
public class CoordinatesToCountry {

    private static final String API_KEY = "d5189589571542e790ff0f6ce983faa2";
    private static final String BASE_URL = "https://api.opencagedata.com/geocode/v1/json";

    /**
     * Returns the country of the given coordinates.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     * @return the country of the given coordinates
     */
    public String getCountryFromCoordinates(double latitude, double longitude) {
        try {
            final String urlString = BASE_URL + "?q=" + latitude + "," + longitude + "&key=" + API_KEY;
            final URL url = new URL(urlString);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            final int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                final BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                final StringBuilder content = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine).append("\n");
                }
                in.close();
                conn.disconnect();

                return parseApiResult(content.toString());
            }
            else {
                System.err.println("Error: " + responseCode);
                return "Country is not available";
            }
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
            return "Unknown Country";
        }
    }

    private String parseApiResult(String apiResult) {
        final String[] lines = apiResult.split("\n");

        if (lines.length > 0) {
            // First line contains the flag and country
            final String firstLine = lines[0].trim();
            final String[] parts = firstLine.split(", ");
            if (parts.length > 1) {
                final String flagAndCountry = parts[parts.length - 1];
                return flagAndCountry;
            }
        }
        return "Unknown Country";
    }
}
