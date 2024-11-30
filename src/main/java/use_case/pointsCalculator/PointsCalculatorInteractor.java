package use_case.pointsCalculator;

import entity.PointsCalculator;
import entity.User;

import java.util.Map;

public class PointsCalculatorInteractor implements
        PointsCalculatorInputBoundary {
    private final PointsCalculatorDataAccessInterface pointsDataAccessObject;
    private final PointsCalculatorOutputBoundary pointsCalculatorPresenter;

    /**
     * Interactor for Points Calculator Use Case.
     *
     * @param pointsDataAccessInterface
     * @param pointsCalculatorPresenter
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

        double distance = getDistance(randomLocation, chosenLocation);
        double timespent = pointsCalculatorInputData.getTimespent();
        System.out.println("timespent" + timespent);
        int hintsused = pointsCalculatorInputData.getHintsused();
        System.out.println("hintsused" + hintsused);

        int[] hintsCosts = {PointsCalculator.HINTS_COST1, PointsCalculator.HINTS_COST2,PointsCalculator.HINTS_COST3};
        int hintsPenalty = 0;
        for (int i = 0; i <hintsused; i++) {
            hintsPenalty += hintsCosts[i];
        }

        int pointsEarned = (int) Math.floor(
                PointsCalculator.MAX_SCORE
                        - distance / PointsCalculator.DISTANCE_DIVIDER
                        - timespent
                        - hintsPenalty);
        System.out.println(pointsEarned);

        String username = pointsDataAccessObject.getCurrentUsername();
        User user = pointsDataAccessObject.get(username);
        System.out.println("current points in interactor: " + user.getPoints());
        pointsDataAccessObject.setCurrentPoints(user.getPoints());
        //updates the current user
        user.addEarnedPoints(pointsEarned);
        //updating the firebase storage
        pointsDataAccessObject.addEarnedPoints(pointsEarned, user);

        String message = "You scored " + pointsEarned + " points!";
        System.out.println("You scored " + pointsEarned + " points!");
        final PointsCalculatorOutputData pointsCalculatorOutputData =
                new PointsCalculatorOutputData(pointsEarned, message, imagepath);
        pointsCalculatorPresenter.prepareSuccessView(pointsCalculatorOutputData);

    }


    public double getDistance(Map<String, Double> randomLocation,
                              Map<String, Double> chosenLocation) {
        if (!randomLocation.containsKey("latitude")
                || !randomLocation.containsKey("longitude")
                || !chosenLocation.containsKey("latitude")
                || !chosenLocation.containsKey("longitude")) {
            throw new IllegalArgumentException(
                    "Random location and Chosen location must contain "
                            + "'latitude' and 'longitude' keys.");
        }


        double lat1 = Math.toRadians(randomLocation.get("latitude"));
        double lon1 = Math.toRadians(randomLocation.get("longitude"));
        double lat2 = Math.toRadians(chosenLocation.get("latitude"));
        double lon2 = Math.toRadians(chosenLocation.get("longitude"));

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Haversine formula to calculate the distance between two points on
        // a sphere
        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(lat1)
                * Math.cos(lat2)
                * Math.pow(Math.sin(dLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        final double radius = 6371;
        double distance = radius * c;
        System.out.println(distance);
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
