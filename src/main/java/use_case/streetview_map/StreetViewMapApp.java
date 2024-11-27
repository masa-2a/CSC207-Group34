package use_case.streetview_map;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.browser.callback.InjectJsCallback;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.js.JsAccessible;
import com.teamdev.jxbrowser.js.JsFunctionCallback;
import com.teamdev.jxbrowser.js.JsObject;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class StreetViewMapApp extends Application {

    public double lat;
    public double lng;

    @Override
    public void start(Stage stage) {

        double[][] randomLocations = {
                {42, -71},
                {34.0361343941081, 108.92676837088668},
                {52.03238908452306, 5.3389802540091695},
                {20.993765372145273, 72.81394739228956},
                {54.11652744386116, 61.57774339280659},
                {65.61559922810795, -37.63672282227504},
                {-20.065367990935233, 57.55697525742677},
                {-3.044898738488582, -59.99374885308898},
                {35.12033659399063, 129.08105506884803},
                {-1.0476618968428848, 36.88403391295389}
        };

        int rand = (int) (Math.random() * 10);
        double[] initLocation = randomLocations[rand];
        System.out.println("This is the lat: " + initLocation[0] + ", " + initLocation[1]);
        lat = initLocation[0];
        lng = initLocation[1];
        System.out.println("This is the global variable: " + lat + ", " + lng);

        Engine engine = Engine.newInstance(EngineOptions.newBuilder(RenderingMode.OFF_SCREEN)
                .licenseKey("OK6AEKNYF2KFR6YGPA0GCYPPB72XLN3MY23BQUDMBVBH16NZHXTGMGGFTQW5FLHQKINRCZEOMFN8HA7VMJ62H2QPSQRIS1NUW21Y2V2H7Q05GH9I5U6APVLUPVHA1C4RMED8O7H7U9Q1BJMFK").build());

        // Create a browser instance.
        Browser browser = engine.newBrowser();

        browser.set(InjectJsCallback.class, params -> {
            JsObject window = params.frame().executeJavaScript("window");
            window.putProperty("java", this);
            return InjectJsCallback.Response.proceed();
        });

        // Create a JavaFX BrowserView.
        BrowserView browserView = BrowserView.newInstance(browser);

        // Load the local HTML file (ensure path is correct).
        String htmlPath = Paths.get("src/main/resources/map.html").toUri().toString();
        browser.navigation().loadUrl(htmlPath);

        browser.mainFrame().ifPresent(frame ->
                frame.executeJavaScript(
                        "window.java = {" +
                                "  getGoalCoordinates: function(goalLat, goalLng) { java.getGoalCoordinates(goalLat, goalLng); }," +
                                "  getUserCoordinates: function(userLat, userLng) { java.getUserCoordinates(userLat, userLng); }," +
                                "  sendGoalLat: function() { java.sendGoalLat(); }," +
                                "  sendGoalLng: function() { java.sendGoalLng(); }" +
                                "};"
                )
        );

        // Setup the JavaFX scene.
        StackPane root = new StackPane();
        root.getChildren().add(browserView);

        Scene scene = new Scene(root);
        stage.setTitle("Street View Map with JxBrowser");

        // Set the application to full-screen mode.
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();

        // Ensure the engine is shut down when the application closes.
        stage.setOnCloseRequest(event -> engine.close());
    }

    @JsAccessible
    public double sendGoalLat() {
        return lat;
    }

    @JsAccessible
    public double sendGoalLng() {
        return lng;
    }

    @JsAccessible
    public void getGoalCoordinates(double goalLat, double goalLng) {
        System.out.println(goalLat);
        System.out.println(goalLng);
    }

    @JsAccessible
    public void getUserCoordinates(double userLat, double userLng) {
        System.out.println(userLat);
        System.out.println(userLng);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
