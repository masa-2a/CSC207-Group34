package data_access;

import java.util.ArrayList;

import entity.player.CommonUser;
import entity.player.User;

/**
 * An abstact DAO. This is used for any future DAO these are
 * the required methods for those to be useful!
 */
public abstract class AbstractDataAccessObject {
    /**
     * Saves user to DAO.
     *
     * @param user user being saved
     */
    public void save(User user) {
    }

    /**
     * Retrieves a user object by username from DAO.
     * @param username user being fetched
     * @return User user
     */
    public User get(String username) {
        return null;
    }

    /**
     * Sets the current logged in users username.
     *
     * @param name user being set.
     */
    public void setCurrentUsername(String name) {
    }

    /**
     * Checks if a User exists in the DAO with the given username.
     *
     * @param username username checking existance for
     * @return boolean
     */
    public boolean existsByName(String username) {
        return false;
    }

    // Change the password for a user

    /**
     * Changes given users password.
     *
     * @param user user changing the password for
     */
    public void changePassword(User user) {
    }

    /**
     * Fetchs the current loggedin users username.
     *
     * @return currentUsername
     */
    public String getCurrentUsername() {
        return null;
    }

    /**
     * Sets the current loggedin User.
     */
    public void setCurrentUser() {
    }

    /**
     * Gets a list of all the users from the DAO.
     *
     * @return ArrayList allUsers
     */
    public ArrayList<CommonUser> returnAllUsers() {
        return null;
    }

}
