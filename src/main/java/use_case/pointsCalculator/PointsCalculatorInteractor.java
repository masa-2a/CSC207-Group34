package use_case.pointsCalculator;

import java.util.Map;

import entity.PointsCalculator;
import entity.User;

/**
 * Interactor for PointsCalculator.
 */
public class PointsCalculatorInteractor implements
        PointsCalculatorInputBoundary {
    private final PointsCalculatorDataAccessInterface pointsDataAccessObject;
    private final PointsCalculatorOutputBoundary pointsCalculatorPresenter;

    /**
     * Interactor for Points Calculator Use Case.
     *
     * @param pointsDataAccessInterface the data access interface for points calc
     * @param pointsCalculatorPresenter the presenter for points calc
     */
    public PointsCalculatorInteractor(PointsCalculatorDataAccessInterface
                                              pointsDataAccessInterface,
                                      PointsCalculatorOutputBoundary
                                              pointsCalculatorPresenter) {
        this.pointsDataAccessObject = pointsDataAccessInterface;
        this.pointsCalculatorPresenter = pointsCalculatorPresenter;
    }

    /**
     * Executes the Points Case Interactor.
     *
     * @param pointsCalculatorInputData the input data
     */
    @Override
    public void execute(PointsCalculatorInputData pointsCalculatorInputData) {
        final Map<String, Double> randomLocation =
                pointsCalculatorInputData.getRandomLocation();
        final Map<String, Double> chosenLocation =
                pointsCalculatorInputData.getChosenLocation();
        final String imagepath = pointsCalculatorInputData.getImagepath();

        final double distance = getDistance(randomLocation, chosenLocation);
        final double timespent = pointsCalculatorInputData.getTimespent();
        final int hintsused = pointsCalculatorInputData.getHintsused();

        System.out.println("Distance: " + distance);
        System.out.println("Timespent: " + timespent);
        System.out.println("Hintsused: " + hintsused);

        final int[] hintsCosts = {PointsCalculator.HINTS_COST1,
                                  PointsCalculator.HINTS_COST2,
                                  PointsCalculator.HINTS_COST3};
        int hintsPenalty = 0;
        for (int i = 0; i < hintsused; i++) {
            hintsPenalty += hintsCosts[i];
        }

        final int pointsEarned = (int) Math.floor(
                PointsCalculator.MAX_SCORE
                        - distance / PointsCalculator.DISTANCE_DIVIDER
                        - timespent
                        - hintsPenalty);
        System.out.println(pointsEarned);

        final String username = pointsDataAccessObject.getCurrentUsername();
        final User user = pointsDataAccessObject.get(username);
        pointsDataAccessObject.setCurrentPoints(user.getPoints());

        // updates the current user
        user.addEarnedPoints(pointsEarned);

        // updating the firebase storage
        pointsDataAccessObject.addEarnedPoints(pointsEarned, user);

        final String message = "You scored " + pointsEarned + " points!";
        final PointsCalculatorOutputData pointsCalculatorOutputData =
                new PointsCalculatorOutputData(pointsEarned, message, imagepath);
        pointsCalculatorPresenter.prepareSuccessView(pointsCalculatorOutputData);

    }

    /**
     * Calculating the distance between randomLocation and chosenLocation.
     * @param randomLocation random generated location
     * @param chosenLocation users guess location
     * @return distance between the 2 inputs
     * @throws IllegalArgumentException if randomLocation or chosenLocation is not formatted correctly
     */
    public double getDistance(Map<String, Double> randomLocation,
                              Map<String, Double> chosenLocation) {
        final String lat = "latitude";
        final String lon = "longitude";
        if (!randomLocation.containsKey(lat)
                || !randomLocation.containsKey(lon)
                || !chosenLocation.containsKey(lat)
                || !chosenLocation.containsKey(lon)) {
            throw new IllegalArgumentException(
                    "Random location and Chosen location must contain "
                            + "'latitude' and 'longitude' keys.");
        }

        final double lat1 = Math.toRadians(randomLocation.get(lat));
        final double lon1 = Math.toRadians(randomLocation.get(lon));
        final double lat2 = Math.toRadians(chosenLocation.get(lat));
        final double lon2 = Math.toRadians(chosenLocation.get(lon));

        final double dLat = lat2 - lat1;
        final double dLon = lon2 - lon1;

        // Haversine formula to calculate the distance between two points on
        // a sphere
        final double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(lat1)
                * Math.cos(lat2)
                * Math.pow(Math.sin(dLon / 2), 2);

        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        final double radius = 6371;
        final double distance = radius * c;

        return distance;
    }

    /**
     * Executes the switch to new round view use case.
     */
    @Override
    public void switchToMenuView() {
        pointsCalculatorPresenter.switchToMenuView();
    }
}
