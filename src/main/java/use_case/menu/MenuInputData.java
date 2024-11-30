package use_case.menu;


public class MenuInputData {
    private String username;
    public MenuInputData(String username) {
        this.setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
