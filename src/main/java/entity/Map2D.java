package entity;

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

/**
 * A class representing the guess map or the 2D map where the guess will be made
 */
public class Map2D {
    private final GeoApiContext context;
    private StaticMapsRequest builtMap;

    public Map2D() {

        // When I create an instance of Map2D I want to build the map
        // Not build it again and again each time I create a new map.
        final String API_KEY = System.getenv("API_KEY");
        this.context = new GeoApiContext.Builder().apiKey(API_KEY).build();
    }

    /**
     * Creating a new 2D map: map with latitude and longitude
     * @param width: the width of the map to be saved
     * @param height: the height of the map to be saved
     * @param latitude: the latitude to create a map of
     * @param longitude: the longitude to create a map of
     * @param zoom: the zoom of the map to be saved
     */
    public void createMap(int width, int height, double latitude, double longitude, int zoom) {

        Size size = new Size(width, height);
        LatLng location = new LatLng(latitude, longitude);

        this.builtMap = StaticMapsApi.newRequest(context, size)
                .center(location)
                .zoom(zoom)
                .maptype(StaticMapType.roadmap);
    }

    /**
     * Creating a get map method
     * @return the builtMap
     */
    public StaticMapsRequest getMap() {
        return builtMap;
    }

    /**
     * This method will save the map to file
     */
    public String saveMap(){
        try {
            byte[] imageBytes = builtMap.await().imageData;
            Files.write(Paths.get("static_map.png"), imageBytes);
            System.out.println("Static map image saved as static_map.png");
            return Paths.get("static_map.png").toString();
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
