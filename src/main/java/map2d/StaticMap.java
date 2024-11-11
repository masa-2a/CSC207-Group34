package map2d;

import com.google.maps.StaticMapsApi;
import com.google.maps.StaticMapsRequest;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Size;

import java.io.IOException;

public class StaticMap {
    static String API_KEY = System.getenv("API_KEY");
    static int width = 10;
    static int height = 10;

    public static void main(String[] args) {
//        StaticMapsApi test = new StaticMapsApi;
        GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
        Size size = new Size(width, height);
        StaticMapsRequest request = new StaticMapsRequest(context);

        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, "1600 Amphitheatre Parkway, Mountain View, CA").await();
            if (results != null && results.length > 0) {
                System.out.println(results[0].formattedAddress);
            } else {
                System.out.println("No results found.");
            }
        } catch (ApiException e) {
            System.err.println("API Exception: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Interrupted Exception: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.err.println("IO Exception: " + e.getMessage());
        }
    }
}