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
import java.nio.file.Path;
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
     * @param guessLat: the latitude of the guess
     * @param guessLong: the longitude of the guess
     * @param answerLat: the latitude of the answer
     * @param answerLong: the longitude of the answer
     * @param guessed: true or false, guessed or not. place 1 marker down?
     * @param answered: true or false, answered or not. place 2 marker and path down?
     */
    public void createMap(int width, int height, double latitude, double longitude,
                          int zoom, double guessLat, double guessLong, double answerLat,
                          double answerLong, boolean guessed, boolean answered) {

        Size size = new Size(width, height);
        LatLng location = new LatLng(latitude, longitude);

        if (answered) {
            LatLng guess = new LatLng(guessLat, guessLong);
            StaticMapsRequest.Markers marker = new StaticMapsRequest.Markers();
            marker.addLocation(guess);
            marker.color("red");
            marker.size(StaticMapsRequest.Markers.MarkersSize.mid);

            LatLng answer = new LatLng(answerLat, answerLong);
            StaticMapsRequest.Markers marker2 = new StaticMapsRequest.Markers();
            marker2.addLocation(answer);
            marker2.color("black");
            marker2.size(StaticMapsRequest.Markers.MarkersSize.mid);

            StaticMapsRequest.Path path = new StaticMapsRequest.Path();
            path.addPoint(guess);
            path.addPoint(answer);
            path.color("black");
            path.weight(10);


            this.builtMap = StaticMapsApi.newRequest(getContext(), size)
                    .center(location)
                    .zoom(zoom)
                    .maptype(StaticMapType.roadmap)
                    .markers(marker)
                    .markers(marker2)
                    .path(path);
        }

        else if (guessed) {
            LatLng guess = new LatLng(guessLat, guessLong);
            StaticMapsRequest.Markers marker = new StaticMapsRequest.Markers();
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
            byte[] imageBytes = getMap().await().imageData;
            Path imgPath = Paths.get("src/main/resources/static_map.png");
            Files.write(imgPath, imageBytes);
            System.out.println("Static map image saved as src/main/resources/static_map.png");
            return imgPath.toString();
        } catch (ApiException e) {
            System.err.println("API Exception: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Interrupted Exception: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.err.println("IO Exception: " + e.getMessage());
        }
        return null;
    }

    public GeoApiContext getContext() {
        return context;
    }
}
