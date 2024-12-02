package app;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {

        new JFXPanel();

        Platform.runLater(() -> System.out.println("JavaFX initialized"));

        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addLoggedInView()
                .addMenuView()
                .addLeaderboardView()
                .addRoundView()
                .addPointsCalculatorView()
                .addMenuUseCase()
                .addSignupUseCase()
                .addLoginUseCase()
                .addLogoutUseCase()
                .addChangePasswordUseCase()
                .addLeaderboardUseCase()
                .addPointsCalculatorUseCase()
                .addRoundUseCase()
                .addMenuUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}