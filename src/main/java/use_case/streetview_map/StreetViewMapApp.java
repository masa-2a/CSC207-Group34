package use_case.streetview_map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

public class StreetViewMapApp extends Application {

    @Override public void start(Stage stage) {
        // Create the WebView
        final WebView webView = new WebView();

        // Obtain the WebEngine from the WebView
        final WebEngine webEngine = webView.getEngine();

        webEngine.setOnAlert(event -> System.out.println("JavaScript Alert: " + event.getData()));

        // Load the googlemap.html file
        URL htmlFileUrl = getClass().getResource("/map.html");
        if (htmlFileUrl != null) {
            webEngine.load(htmlFileUrl.toString());
        } else {
            System.out.println("Error: map.html not found in resources.");
        }

        // create scene
        stage.setTitle("Web Map");
        Scene scene = new Scene(webView,1000,700, Color.web("#666970"));
        stage.setScene(scene);
        // show stage
        stage.show();
    }

    static { // use system proxy settings when standalone application
        System.setProperty("java.net.useSystemProxies", "true");
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}