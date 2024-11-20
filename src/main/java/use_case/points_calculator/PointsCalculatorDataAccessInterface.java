package use_case.points_calculator;


import entity.User;

public interface PointsCalculatorDataAccessInterface {

    /**
     * Returns the username of the current user of the application.
     * @return the username of the current user
     */
    String getCurrentUsername();

    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);

}
