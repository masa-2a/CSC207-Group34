package use_case.streetview_map;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class StreetViewMapApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a WebView
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);

        // Load the map.html file
        try {
            // Load map.html from the resources folder
            URL htmlFileUrl = getClass().getResource("/map.html");
            if (htmlFileUrl != null) {
                webEngine.load(htmlFileUrl.toExternalForm());
            } else {
                System.out.println("Failed to find map.html in resources.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Error loading map.html: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Add debug listeners to WebEngine
        webEngine.setOnError(event -> System.out.println("ERROR: " + event.getMessage()));
        webEngine.setOnAlert(event -> System.out.println("ALERT: " + event.getData()));
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.FAILED) {
                System.out.println("Failed to load: " + webEngine.getLocation());
            } else if (newState == Worker.State.SUCCEEDED) {
                System.out.println("Successfully loaded: " + webEngine.getLocation());
            }
        });

        // Set up the scene and stage
        Scene scene = new Scene(webView, 1200, 800); // Set the desired window size
        primaryStage.setTitle("Google Maps Street View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
