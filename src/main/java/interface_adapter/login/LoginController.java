package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInputData;

/**
 * The controller for the Login Use Case.
 */
public class LoginController {

    private final LoginInputBoundary loginUseCaseInteractor;
    private final MenuInputBoundary menuInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor,
                           MenuInputBoundary menuInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
        this.menuInteractor = menuInteractor;
    }

    /**
     * Executes the Login Use Case.
     * @param username the username of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String username, String password) {
        final LoginInputData loginInputData = new LoginInputData(
                username, password);
        final MenuInputData menuInputData = new MenuInputData(username);

        loginUseCaseInteractor.execute(loginInputData);
        menuInteractor.execute(menuInputData);

    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToSignupView() {
        loginUseCaseInteractor.switchToSignupView();
    }
}
