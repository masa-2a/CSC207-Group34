package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.FirestoreDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.main_menu.MenuController;
import interface_adapter.main_menu.MenuPresenter;
import interface_adapter.main_menu.MenuViewModel;
import interface_adapter.map2d.Map2DController;
import interface_adapter.map2d.Map2DPresenter;
import interface_adapter.map2d.Map2DViewModel;
import interface_adapter.round.RoundController;
import interface_adapter.round.RoundPresenter;
import interface_adapter.round.RoundViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.streetview_map.StreetViewMapViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.map2d.Map2DInputBoundary;
import use_case.map2d.Map2DOutputBoundary;
import use_case.map2d.Map2DUseCaseInteractor;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.round.RoundInputBoundary;
import use_case.round.RoundInteractor;
import use_case.round.RoundOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final FirestoreDataAccessObject userDataAccessObject = new FirestoreDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private MenuView menuView;
    private MenuViewModel menuViewModel;

    private StreetViewMapViewModel streetViewMapViewModel;
    private MapView mapView;
  
    private Map2DView map2DView;
    private Map2DViewModel map2DViewModel;
    private RoundView roundView;
    private RoundViewModel roundViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the applicaåtion.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds a Round View to the application
     * @return this builder
     */
    public AppBuilder addRoundView() {
        this.roundViewModel = new RoundViewModel();
        this.roundView = new RoundView(roundViewModel);
        cardPanel.add(roundView,roundView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addMenuView() {
        menuViewModel = new MenuViewModel();
        menuView = new MenuView(menuViewModel);
        cardPanel.add(menuView,menuView.getViewName());
        return this;
    }

    /**
     * Adds a Map2D View to the application
     * @return this builder
     */
    public AppBuilder addMap2DView() {
        map2DViewModel = new Map2DViewModel();
        map2DView = new Map2DView(map2DViewModel);
        cardPanel.add(map2DView,map2DView.getViewName());
        return this;
    }


    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel, signupViewModel, menuViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Menu Use Case to the application.
     * @return this builder
     */
    public AppBuilder addMenuUseCase() {
        final MenuOutputBoundary menuOutputBoundary = new MenuPresenter(menuViewModel, loggedInViewModel,
                viewManagerModel, roundViewModel);
        final MenuInputBoundary menuInteractor = new MenuInteractor(menuOutputBoundary);

        final MenuController menuController = new MenuController(menuInteractor);
        menuView.setMenuController(menuController);
        return this;
    }


    /**
     * Adds the Map2D Use Case to the application.
     * @return this builder
     */
    public AppBuilder addMap2DUseCase() {
        final Map2DOutputBoundary map2DOutputBoundary = new Map2DPresenter(viewManagerModel,
                map2DViewModel);
        final Map2DInputBoundary map2DUseCaseInteractor = new Map2DUseCaseInteractor(
                map2DOutputBoundary);

        final Map2DController controller = new Map2DController(map2DUseCaseInteractor);
        map2DView.setMap2DController(controller);
        return this;
    }


    /**
     * Adds the Round Use Case to the application.
     * @return this builder
     */
    public AppBuilder addRoundUseCase() {
        final RoundOutputBoundary roundOutputBoundary = new RoundPresenter(roundViewModel, map2DViewModel,
                viewManagerModel);
        final RoundInputBoundary roundUseCaseInteractor = new RoundInteractor(
                roundOutputBoundary);

        final RoundController roundController = new RoundController(roundUseCaseInteractor);
        roundView.setRoundController(roundController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
