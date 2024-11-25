package map2d;

import com.google.maps.GeoApiContext;
import com.google.maps.StaticMapsApi;
import com.google.maps.StaticMapsRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.Size;
import com.google.maps.StaticMapsRequest.StaticMapType;
import com.google.maps.model.LatLng;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StaticMap {
    private final String API_KEY = System.getenv("API_KEY");
    double latitude; // Example latitude
    double longitude; // Example longitude
    StaticMapsRequest mapsRequest;

    public StaticMap() {
        this.latitude = 0;
        this.longitude = 0;
    }

    public StaticMapsRequest getMap() {
//        if (API_KEY == null || API_KEY.isEmpty()) {
//            System.err.println("API_KEY environment variable is not set.");
//            return;
//        }

        GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
        int width = 600;
        int height = 400;
        Size size = new Size(width, height);
        LatLng location = new LatLng(latitude, longitude);

        StaticMapsRequest request = StaticMapsApi.newRequest(context, size)
                .center(location)
                .zoom(2)
                .maptype(StaticMapType.roadmap);

        this.mapsRequest = request;
        return request;
    }

    public void saveMap(){

        try {
            byte[] imageBytes = mapsRequest.await().imageData;
            Files.write(Paths.get("static_map.png"), imageBytes);
            System.out.println("Static map image saved as static_map.png");
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