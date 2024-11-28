package app;

import firebase.FirebaseInitializer;
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

        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addLoggedInView()
                .addMenuView()
                .addRoundView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addLogoutUseCase()
                .addChangePasswordUseCase()
                .addRoundUseCase()
                .addMenuUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
