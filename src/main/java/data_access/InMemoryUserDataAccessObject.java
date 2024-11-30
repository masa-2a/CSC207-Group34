package data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.CommonUser;
import entity.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.leaderboard.LeaderboardUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface, LeaderboardUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public void setCurrentUser(User user) {
        this.currentUsername = user.getName();
    }

    @Override
    public ArrayList<CommonUser> returnAllUsers() {
        ArrayList<CommonUser> allUsers = new ArrayList<>();
        for (User user : users.values()) {
            allUsers.add(new CommonUser(user.getName(), user.getPassword(), user.getPoints(), user.getNumberOfGames()));
        }
        return allUsers;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }
}
