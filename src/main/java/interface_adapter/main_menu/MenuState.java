package interface_adapter.main_menu;

/**
 * State for the Menu Use Case.
 */
public class MenuState {
    private String currentUsername;

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }
}
