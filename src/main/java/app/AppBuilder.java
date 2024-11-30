package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.FirestoreDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardPresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.main_menu.MenuController;
import interface_adapter.main_menu.MenuPresenter;
import interface_adapter.main_menu.MenuViewModel;
import interface_adapter.points_calculator.PointsCalculatorController;
import interface_adapter.points_calculator.PointsCalculatorPresenter;
import interface_adapter.points_calculator.PointsCalculatorViewModel;
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
import use_case.countdown.CountdownInputBoundary;
import use_case.countdown.CountdownInteractor;
import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.pointsCalculator.PointsCalculatorInputBoundary;
import use_case.pointsCalculator.PointsCalculatorInteractor;
import use_case.pointsCalculator.PointsCalculatorOutputBoundary;
import use_case.round.*;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.streetview_map.StreetViewMapInputBoundary;
import use_case.streetview_map.StreetViewMapInteractor;
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
    private PointsCalculatorViewModel pointsCalculatorViewModel;
    private PointsCalculatorView pointsView;
    private LeaderboardViewModel leaderboardViewModel;
    private LeaderboardView leaderboardView;

    private StreetViewMapViewModel streetViewMapViewModel;
    private RoundView roundView;
    private RoundViewModel roundViewModel;
    private RoundInputBoundary roundUseCaseInteractor;
    private LeaderboardInputBoundary leaderboardInteractor;
    private MenuInputBoundary menuInteractor;
    private PointsCalculatorInputBoundary pointsInteractor;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
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

    public AppBuilder addLeaderboardView() {
        this.leaderboardViewModel = new LeaderboardViewModel();
        this.leaderboardView = new LeaderboardView(leaderboardViewModel);
        cardPanel.add(leaderboardView, leaderboardView.getViewName());
        return this;
    }

    public AppBuilder addPointsCalculatorView() {
        this.pointsCalculatorViewModel = new PointsCalculatorViewModel();
        this.pointsView = new PointsCalculatorView(pointsCalculatorViewModel);
        cardPanel.add(pointsView, pointsView.getViewName());
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

        final LoginController loginController = new LoginController(loginInteractor, menuInteractor);
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
     * Adds leaderboard Use Case to the application
     * @return this builder
     */
    public AppBuilder addLeaderboardUseCase() {
        final LeaderboardOutputBoundary leaderboardPresenter = new LeaderboardPresenter(leaderboardViewModel,
                viewManagerModel, menuViewModel);

        leaderboardInteractor =
                new LeaderboardInteractor(leaderboardPresenter, userDataAccessObject);

        final LeaderboardController leaderboardController = new LeaderboardController(leaderboardInteractor);

        leaderboardView.setLeaderboardController(leaderboardController);
        menuView.setLeaderboardController(leaderboardController);
        return this;
    }

     /**
     * Adds the Menu Use Case to the application.
     * @return this builder
     */
    public AppBuilder addMenuUseCase() {

        //menu presenter
        final MenuOutputBoundary menuOutputBoundary = new MenuPresenter(menuViewModel, loggedInViewModel,
                viewManagerModel, roundViewModel, leaderboardViewModel);
        //menu interactor
        menuInteractor = new MenuInteractor(menuOutputBoundary);



        final MenuController menuController = new MenuController(menuInteractor, leaderboardInteractor);
        menuView.setMenuController(menuController);
        return this;
    }

    /**
     * Adds the Round Use Case to the application.
     * @return this builder
     */
    public AppBuilder addRoundUseCase() {
        // Streetview Stuff

        final StreetViewMapInputBoundary mapInteractor = new StreetViewMapInteractor();

        final RoundOutputBoundary roundOutputBoundary = new RoundPresenter(roundViewModel,
                viewManagerModel, pointsCalculatorViewModel, pointsInteractor );
        final RoundDataAccessInterface roundDataAccess = new
                RoundDataAccess("src/main/resources/rand_locations.json");
        roundUseCaseInteractor = new RoundInteractor(mapInteractor,
                roundOutputBoundary, roundDataAccess);

        CountdownInputBoundary countdownInteractor = new
                CountdownInteractor(roundOutputBoundary);

        final RoundController roundController = new RoundController(roundUseCaseInteractor,
                countdownInteractor, pointsInteractor);


        roundView.setRoundController(roundController);
        return this;
    }

    public AppBuilder addPointsCalculatorUseCase() {
        //points calc presenter
        final PointsCalculatorOutputBoundary pointsPresenter = new PointsCalculatorPresenter(pointsCalculatorViewModel,
                viewManagerModel, menuViewModel);
        //POINTS interactor
        pointsInteractor = new PointsCalculatorInteractor(userDataAccessObject, pointsPresenter);

        final PointsCalculatorController pointsController = new PointsCalculatorController(pointsInteractor);
        pointsView.setPointsCalculatorController(pointsController);
        return this;
    }



    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Map Master");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }


}
