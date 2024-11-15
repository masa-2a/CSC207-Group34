package use_case.streetview_map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class StreetViewMapApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a WebView
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // HTML code for the Google Map Street View
        String htmlContent = """
        <!doctype html>
        <html>
        <head>
          <title>Street View split-map-panes</title>
        </head>

        <style>
          html, body {
            height: 100%;
            margin: 0;
            padding: 0;
          }

          #map, #pano {
            float: left;
            height: 100%;
            width: 50%;
          }
        </style>

        <body>
        <div id="map"></div>
        <div id="pano"></div>

        <script
                src="https://maps.googleapis.com/maps/api/js?key=YOUR_GOOGLE_MAPS_API_KEY&callback=initialize"
                defer
        ></script>

        <script>
          let v = 1;
          let panorama;
          function initialize() {
            const fenway = { lat: 42.345573, lng: -71.098326 }
            const map = new google.maps.Map(document.getElementById("map"), {
              center: fenway,
              zoom: 14,
            })
            const panorama = new google.maps.StreetViewPanorama(
              document.getElementById("pano"),
              {
                position: fenway,
                pov: {
                  heading: 34,
                  pitch: 10,
                },
              },
            )

            map.setStreetView(panorama)
          }

          window.initialize = initialize
        </script>
        </body>
        </html>
        """;

        // Load the HTML content into the WebView
        webEngine.loadContent(htmlContent);

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
