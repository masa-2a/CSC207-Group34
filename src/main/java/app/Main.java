package app;

import javafx.application.Platform;

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
        Platform.startup(() -> {});

//        Platform.startup(() -> {});

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
                                            .addRoundUseCase()
                                            .addPointsCalculatorUseCase()
                                            .addMenuUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
