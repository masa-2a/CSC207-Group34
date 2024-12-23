package entity.map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.maps.GeoApiContext;
import com.google.maps.StaticMapsApi;
import com.google.maps.StaticMapsRequest;
import com.google.maps.StaticMapsRequest.StaticMapType;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.Size;

/**
 * A class representing the guess map or the 2D map where the guess will be made.
 */
public class Map2D {
    private static final String API_KEY = System.getenv("API_KEY");
    private static final int PATH_WEIGHT = 10;
    private final GeoApiContext context;
    private StaticMapsRequest builtMap;

    public Map2D() {

        // When I create an instance of Map2D I want to build the map
        // Not build it again and again each time I create a new map.
        this.context = new GeoApiContext.Builder().apiKey(API_KEY).build();
    }

    /**
     * Creating a new 2D map: map with latitude and longitude.
     * @param width the width of the map to be saved
     * @param height the height of the map to be saved
     * @param latitude the latitude to create a map of
     * @param longitude the longitude to create a map of
     * @param zoom the zoom of the map to be saved
     * @param guessLat the latitude of the guess
     * @param guessLong the longitude of the guess
     * @param answerLat the latitude of the answer
     * @param answerLong the longitude of the answer
     * @param guessed true or false, guessed or not. place 1 marker down?
     * @param answered true or false, answered or not. place 2 marker and path down?
     */
    public void createMap(int width, int height, double latitude, double longitude,
                          int zoom, double guessLat, double guessLong, double answerLat,
                          double answerLong, boolean guessed, boolean answered) {

        final Size size = new Size(width, height);
        final LatLng location = new LatLng(latitude, longitude);

        if (answered) {
            final LatLng guess = new LatLng(guessLat, guessLong);
            final StaticMapsRequest.Markers marker = new StaticMapsRequest.Markers();
            marker.addLocation(guess);
            marker.color("red");
            marker.size(StaticMapsRequest.Markers.MarkersSize.mid);

            final LatLng answer = new LatLng(answerLat, answerLong);
            final StaticMapsRequest.Markers marker2 = new StaticMapsRequest.Markers();
            marker2.addLocation(answer);
            marker2.color("black");
            marker2.size(StaticMapsRequest.Markers.MarkersSize.mid);

            final StaticMapsRequest.Path path = new StaticMapsRequest.Path();
            path.addPoint(guess);
            path.addPoint(answer);
            path.color("black");
            path.weight(PATH_WEIGHT);

            this.builtMap = StaticMapsApi.newRequest(getContext(), size)
                    .center(location)
                    .zoom(zoom)
                    .maptype(StaticMapType.roadmap)
                    .markers(marker)
                    .markers(marker2)
                    .path(path);
        }

        else if (guessed) {
            final LatLng guess = new LatLng(guessLat, guessLong);
            final StaticMapsRequest.Markers marker = new StaticMapsRequest.Markers();
            marker.addLocation(guess);
            marker.color("red");
            marker.size(StaticMapsRequest.Markers.MarkersSize.mid);

            this.builtMap = StaticMapsApi.newRequest(getContext(), size)
                    .center(location)
                    .zoom(zoom)
                    .maptype(StaticMapType.roadmap)
                    .markers(marker);
        }

        else {
            this.builtMap = StaticMapsApi.newRequest(getContext(), size)
                    .center(location)
                    .zoom(zoom)
                    .maptype(StaticMapType.roadmap);
        }
    }

    /**
     * Creating a get map method.
     * @return the builtMap
     */
    public StaticMapsRequest getMap() {
        return builtMap;
    }

    /**
     * This method will save the map to file.
     * @return the path of the saved map
     */
    public String saveMap() {
        try {
            final byte[] imageBytes = getMap().await().imageData;
            final Path imgPath = Paths.get("src/main/resources/static_map.png");
            Files.write(imgPath, imageBytes);
            System.out.println("Static map image saved as src/main/resources/static_map.png");
            return imgPath.toString();
        }
        catch (ApiException apiException) {
            System.err.println("API Exception: " + apiException.getMessage());
        }
        catch (InterruptedException interruptedException) {
            System.err.println("Interrupted Exception: " + interruptedException.getMessage());
            Thread.currentThread().interrupt();
        }
        catch (IOException ioException) {
            System.err.println("IO Exception: " + ioException.getMessage());
        }
        return null;
    }

    public GeoApiContext getContext() {
        return context;
    }
}
