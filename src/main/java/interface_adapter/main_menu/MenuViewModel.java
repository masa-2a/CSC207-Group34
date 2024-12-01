package interface_adapter.main_menu;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Menu Use Case.
 */
public class MenuViewModel extends ViewModel<MenuState> {
    public static final String TITLE_LABEL = "Main Menu";
    public static final String NEW_ROUND_BUTTON_LABEL = "New Round";
    public static final String LEADERBOARD = "Leaderboard";
    public static final String TO_LOGOUT_BUTTON_LABEL = "To Logout";

    public MenuViewModel() {
        super("Menu View");
        setState(new MenuState());
    }
}
