package app;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        // Initialize JavaFX toolkit
        JFXPanel jfxPanel = new JFXPanel();
        // Schedule JavaFX tasks
        Platform.runLater(() -> {
            System.out.println("JavaFX initialized");
            // Launch your JavaFX application here if needed
        });

        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addMenuView()
                                            .addLeaderboardView()
                                            .addRoundView()
                                            .addMenuUseCase()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addLogoutUseCase()
                                            .addChangePasswordUseCase()
                                            .addLeaderboardUseCase()
                                            .addRoundUseCase()
                                            .addMenuUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
