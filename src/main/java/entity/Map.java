package entity;

import java.nio.file.Paths;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.browser.callback.InjectJsCallback;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.js.JsAccessible;
import com.teamdev.jxbrowser.js.JsObject;
import com.teamdev.jxbrowser.navigation.event.LoadFinished;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The Map class is responsible for displaying the map in the application.
 */
public class Map extends Application {
    private static final int STAGE_WIDTH = 1000;
    private static final int STAGE_HEIGHT = 800;

    private double userLatitude;
    private double userLongitude;
    private double goalLongitude;
    private double goalLatitude;
    private Stage stage;

    /**
     * Starts the application.
     * @param primaryStage the first stage variable to start map loading
     */
    @Override
    public void start(Stage primaryStage) {
        loadMap(primaryStage);
    }

    /**
     * Gives the coordinates of the goal to the map.
     * @param goalLat the latitude of the goal location
     * @param goalLng the longitude of the goal location
     */
    public void giveCoords(double goalLat, double goalLng) {
        setGoalLatitude(goalLat);
        setGoalLongitude(goalLng);
    }

    /**
     * Logs a message to the console.
     * @param message the message to log
     */
    public void logMessage(String message) {
        System.out.println("JS Log: " + message);
    }

    /**
     * Closes the application.
     */
    @JsAccessible
    public void closeApplication() {
        System.out.println("Close application");
        Platform.runLater(() -> {
            if (stage != null) {
                stage.close();
            }
            Platform.exit();
        });
    }

    /**
     * Loads the map.
     * @param primaryStage the stage to load the map on
     */
    public void loadMap(Stage primaryStage) {
        stage = primaryStage;
        final Engine engine = Engine.newInstance(EngineOptions.newBuilder(RenderingMode.OFF_SCREEN)
                .licenseKey("OK6AEKNYF2KFR6YGPA0GCYPPB72XLN3MY23BQUDMBVBH16NZHXTGMGGFTQW5FLHQKINRCZEOMFN8HA7V"
                        + "MJ62H2QPSQRIS1NUW21Y2V2H7Q05GH9I5U6APVLUPVHA1C4RMED8O7H7U9Q1BJMFK").build());

        final Browser browser = engine.newBrowser();

        browser.set(InjectJsCallback.class, params -> {
            final JsObject window = params.frame().executeJavaScript("window");
            if (window != null) {
                window.putProperty("java", this);
                params.frame().executeJavaScript("console.log(window.java);");
            }
            return InjectJsCallback.Response.proceed();
        });

        final BrowserView browserView = BrowserView.newInstance(browser);
        final String htmlPath = Paths.get("src/main/resources/map.html").toUri().toString();
        browser.navigation().loadUrl(htmlPath);

        browser.navigation().on(LoadFinished.class, event -> {
            browser.mainFrame().ifPresent(frame -> {
                frame.executeJavaScript(
                        "if (window.java) {"
                                + "    window.java.logMessage('Java object is available in JavaScript.');"
                                + "} else {"
                                + "    alert('Java object is NOT available in JavaScript.');" + "}"
                );
            });
        });

        final StackPane root = new StackPane();
        root.getChildren().add(browserView);

        final Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Street View Map with JxBrowser");
        primaryStage.setWidth(STAGE_WIDTH);
        primaryStage.setHeight(STAGE_HEIGHT);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> engine.close());

    }

    /**
     * Sets the user's latitude in JavaScript.
     * @param userLatitude the user's latitude
     */
    @JsAccessible
    public void setUserLatitude(double userLatitude) {
        this.userLatitude = userLatitude;
        System.out.println("User Latitude set to: " + userLatitude);
    }

    /**
     * Sets the user's longitude in JavaScript.
     * @param userLongitude the user's longitude
     */
    @JsAccessible
    public void setUserLongitude(double userLongitude) {
        this.userLongitude = userLongitude;
        System.out.println("User Longitude set to: " + userLongitude);
    }

    /**
     * Sends the user's latitude to JavaScript.
     * @return the user's latitude
     */
    @JsAccessible
    public double sendGoalLatitude() {
        return goalLatitude;
    }

    /**
     * Sends the user's longitude to JavaScript.
     * @return the user's longitude
     */
    @JsAccessible
    public double sendGoalLongitude() {
        return goalLongitude;
    }

    public double getUserLongitude() {
        return userLongitude;
    }

    public double getUserLatitude() {
        return userLatitude;
    }

    public void setGoalLongitude(double goalLongitude) {
        this.goalLongitude = goalLongitude;
    }

    public void setGoalLatitude(double goalLatitude) {
        this.goalLatitude = goalLatitude;
    }

}
