package use_case.streetview_map;

import com.teamdev.jxbrowser.browser.Browser;

import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class StreetViewMapApp extends Application {
    @Override
    public void start(Stage stage) {
        // Initialize the JxBrowser engine.
        Engine engine = Engine.newInstance(EngineOptions.newBuilder(RenderingMode.OFF_SCREEN)
                .licenseKey("OK6AEKNYF2KFR6YGPA0GCYPPB72XLN3MY23BQUDMBVBH16NZHXTGMGGFTQW5FLHQKINRCZEOMFN8HA7VMJ62H2QPSQRIS1NUW21Y2V2H7Q05GH9I5U6APVLUPVHA1C4RMED8O7H7U9Q1BJMFK").build());

        // Create a browser instance.
        Browser browser = engine.newBrowser();

        // Create a JavaFX BrowserView.
        BrowserView browserView = BrowserView.newInstance(browser);

        // Load the local HTML file.
        String htmlPath = Paths.get("src/main/resources/map.html").toUri().toString();
        browser.navigation().loadUrl(htmlPath);

        // Setup the JavaFX scene.
        StackPane root = new StackPane();
        root.getChildren().add(browserView);

        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Web Map - JxBrowser");
        stage.setScene(scene);
        stage.show();

        // Ensure the engine is shut down when the application closes.
        stage.setOnCloseRequest(event -> engine.close());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
