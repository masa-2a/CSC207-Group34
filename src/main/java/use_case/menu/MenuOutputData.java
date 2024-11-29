package use_case.menu;

public class MenuOutputData {
    private String username;
    public MenuOutputData(String username) {
        this.setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
