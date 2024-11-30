package use_case.pointsCalculator;

import entity.User;

/**
 * Data Access Interface for Points Calculator Use Case.
 */
public interface PointsCalculatorDataAccessInterface {

    /**
     * Returns the username of the current user of the application.
     *
     * @return the username of the current user
     */
    String getCurrentUsername();

    /**
     * Returns the user with the given username.
     *
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);

    /**
     * Adds the points earned to the user.
     * @param pointsEarned the number of points earned
     * @param user the user playing
     */
    void addEarnedPoints(int pointsEarned, User user);

    /**
     * Sets the current points. This is called twice, once to set the points
     * before the round is played and then once after you earn your points.
     * @param points the number of points
     */
    void setCurrentPoints(int points);

    /**
     * Getter method.
     * @return current points
     */
    int getCurrentPoints();

}
