package use_case.points_calculator;


import entity.PointsCalculator;
import entity.User;

import java.util.Map;

public class PointsCalculatorInteractor implements PointsCalculatorInputBoundary {
    private final PointsCalculatorDataAccessInterface pointsDataAccessObject;
    private final PointsCalculatorOutputBoundary pointsCalculatorPresenter;

    public PointsCalculatorInteractor(PointsCalculatorDataAccessInterface pointsDataAccessInterface, PointsCalculatorOutputBoundary pointsCalculatorPresenter) {
        this.pointsDataAccessObject = pointsDataAccessInterface;
        this.pointsCalculatorPresenter = pointsCalculatorPresenter;
    }

    /*
    Calculating the distance between two points on earth,
    the random location and the chosen location
     */

    @Override
    public void execute(PointsCalculatorInputData pointsCalculatorInputData) {
        final Map<String, Double> randomLocation = pointsCalculatorInputData.getRandomLocation();
        final Map<String, Double> chosenLocation = pointsCalculatorInputData.getChosenLocation();

        double distance = PointsCalculator.calculateDistance(randomLocation, chosenLocation);
        double timespent = pointsCalculatorInputData.getTimespent();
        int hintsused = pointsCalculatorInputData.getHintsused();
        int points = (int) Math.floor(PointsCalculator.MAX_SCORE - distance / 10 - timespent - PointsCalculator.HINTS_COST * hintsused);

        String username = pointsDataAccessObject.getCurrentUsername();
        User user = pointsDataAccessObject.get(username);
        user.addPoints(points);


        String message = "You scored " + points + "points!";
        final PointsCalculatorOutputData pointsCalculatorOutputData = new PointsCalculatorOutputData(points, message);
        pointsCalculatorPresenter.prepareSuccessView(pointsCalculatorOutputData);

    }
}