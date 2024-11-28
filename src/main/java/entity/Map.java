package entity;

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
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Map extends Application {
    private double userLatitude;
    private double userLongitude;
    private double goalLongitude;
    private double goalLatitude;

    @Override
    public void start(Stage primaryStage) {
        loadMap();
    }

    public void giveCoords(double goalLat, double goalLng) {
        setGoalLatitude(goalLat);
        setGoalLongitude(goalLng);
    }

    public void loadMap() {
        Engine engine = Engine.newInstance(EngineOptions.newBuilder(RenderingMode.OFF_SCREEN)
                .licenseKey("OK6AEKNYF2KFR6YGPA0GCYPPB72XLN3MY23BQUDMBVBH16NZHXTGMGGFTQW5FLHQKINRCZEOMFN8HA7VMJ62H2QPSQRIS1NUW21Y2V2H7Q05GH9I5U6APVLUPVHA1C4RMED8O7H7U9Q1BJMFK").build());

        Browser browser = engine.newBrowser();

        browser.set(InjectJsCallback.class, params -> {
            JsObject window = params.frame().executeJavaScript("window");
            assert window != null;
            window.putProperty("java", this);
            return InjectJsCallback.Response.proceed();
        });

        BrowserView browserView = BrowserView.newInstance(browser);
        String htmlPath = Paths.get("src/main/resources/map.html").toUri().toString();
        browser.navigation().loadUrl(htmlPath);

        // Add a listener to execute JavaScript after the page has loaded.
        browser.navigation().on(LoadFinished.class, event -> {
            browser.mainFrame().ifPresent(frame ->
                    frame.executeJavaScript(
                            "window.java = {" +
                                    "  setUserLatitude: function(userLatitude) { java.setUserLatitude(userLatitude); }," +
                                    "  setUserLongitude: function(userLongitude) { java.setUserLongitude(userLongitude); }," +
                                    "  sendGoalLatitude: function() { java.sendGoalLatitude(); }," +
                                    "  sendGoalLongitude: function() { java.sendGoalLongitude(); }" +
                                    "};"
                    )
            );
        });

        StackPane root = new StackPane();
        root.getChildren().add(browserView);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Street View Map with JxBrowser");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> engine.close());
    }

    @JsAccessible
    public void setUserLatitude(double userLatitude) {
        this.userLatitude = userLatitude;
    }

    @JsAccessible
    public void setUserLongitude(double userLongitude) {
        this.userLongitude = userLongitude;
    }

    @JsAccessible
    public double sendGoalLatitude() {
        return goalLatitude;
    }

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
