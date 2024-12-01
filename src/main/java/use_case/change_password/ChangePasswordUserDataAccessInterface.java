package use_case.change_password;

import entity.player.User;

/**
 * The interface of the DAO for the Change Password Use Case.
 */
public interface ChangePasswordUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     * @param user the user whose password is to be updated
     */
    void changePassword(User user);

    /**
     * Gets the current user.
     * @return the current user
     */
    User getCurrentUser();
}
