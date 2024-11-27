package view;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.browser.callback.InjectJsCallback;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.js.JsAccessible;
import com.teamdev.jxbrowser.js.JsObject;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import interface_adapter.streetview_map.StreetViewMapController;
import interface_adapter.streetview_map.StreetViewMapPresenter;
import interface_adapter.streetview_map.StreetViewMapViewModel;
import use_case.streetview_map.StreetViewMapInteractor;
import use_case.streetview_map.StreetViewMapOutputBoundary;
import use_case.streetview_map.StreetViewMapOutputData;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class MapView {
    private final String viewName = "Street View Map Game";
    private final StreetViewMapController controller;

    public MapView(StreetViewMapViewModel viewModel) {
        // Create presenter and use case output boundary
        StreetViewMapPresenter presenter = new StreetViewMapPresenter();
        StreetViewMapOutputBoundary outputBoundary = new StreetViewMapOutputBoundary() {
            @Override
            public void present(StreetViewMapOutputData outputData) {
               // controller.presentCoordinates(outputData);
            }
        };

        // Create interactor and controller
        StreetViewMapInteractor interactor = new StreetViewMapInteractor(outputBoundary);
        this.controller = new StreetViewMapController(interactor, presenter);
    }

    public void start(Stage stage) {
        // Initialize the JxBrowser engine.
        Engine engine = Engine.newInstance(EngineOptions.newBuilder(RenderingMode.OFF_SCREEN)
                .licenseKey("OK6AEKNYF2KFR6YGPA0GCYPPB72XLN3MY23BQUDMBVBH16NZHXTGMGGFTQW5FLHQKINRCZEOMFN8HA7VMJ62H2QPSQRIS1NUW21Y2V2H7Q05GH9I5U6APVLUPVHA1C4RMED8O7H7U9Q1BJMFK").build());

        // Create a browser instance.
        Browser browser = engine.newBrowser();

        browser.set(InjectJsCallback.class, params -> {
            JsObject window = params.frame().executeJavaScript("window");
            window.putProperty("java", new MapView(new StreetViewMapViewModel()));
            return InjectJsCallback.Response.proceed();
        });

        // Create a JavaFX BrowserView.
        BrowserView browserView = BrowserView.newInstance(browser);

        // Load the local HTML file (ensure path is correct).
        String htmlPath = Paths.get("src/main/resources/map.html").toUri().toString();
        browser.navigation().loadUrl(htmlPath);

        // Expose the Java object to JavaScript.
        browser.mainFrame().ifPresent(frame ->
                frame.executeJavaScript("window.java = { printCoordinates: function(totalDistance) { java.printCoordinates(totalDistance); }};")
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

    // Java method to be called from JavaScript (print the coordinates)
    @JsAccessible
    public void printCoordinates(double totalDistance) {
        controller.printCoordinates(totalDistance);
    }

    public String getMapName() {
        return viewName;
    }
}