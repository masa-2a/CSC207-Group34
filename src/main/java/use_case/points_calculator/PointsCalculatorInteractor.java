package use_case.points_calculator;


import entity.PointsCalculator;
import entity.User;
import java.util.Map;
import static use_case.points_calculator.calculateDistance.getDistance;

public class PointsCalculatorInteractor implements PointsCalculatorInputBoundary {
    private final PointsCalculatorDataAccessInterface pointsDataAccessObject;
    private final PointsCalculatorOutputBoundary pointsCalculatorPresenter;

    public PointsCalculatorInteractor(PointsCalculatorDataAccessInterface pointsDataAccessInterface, PointsCalculatorOutputBoundary pointsCalculatorPresenter) {
        this.pointsDataAccessObject = pointsDataAccessInterface;
        this.pointsCalculatorPresenter = pointsCalculatorPresenter;
    }

    @Override
    public void execute(PointsCalculatorInputData pointsCalculatorInputData) {
        final Map<String, Double> randomLocation = pointsCalculatorInputData.getRandomLocation();
        final Map<String, Double> chosenLocation = pointsCalculatorInputData.getChosenLocation();
        final String imagepath = pointsCalculatorInputData.getImagepath();

        double distance = getDistance(randomLocation, chosenLocation);
        double timespent = pointsCalculatorInputData.getTimespent();
        int hintsused = pointsCalculatorInputData.getHintsused();
        int points = (int) Math.floor(PointsCalculator.MAX_SCORE - distance / 10 - timespent - PointsCalculator.HINTS_COST * hintsused);

        String username = pointsDataAccessObject.getCurrentUsername();
        User user = pointsDataAccessObject.get(username);
        user.addPoints(points);

        String message = "You scored " + points + "points!";
        final PointsCalculatorOutputData pointsCalculatorOutputData = new PointsCalculatorOutputData(points, message, imagepath);
        pointsCalculatorPresenter.prepareSuccessView(pointsCalculatorOutputData);

    }

    /**
     * Executes the switch to new round view use case.
     */
    @Override
    public void switchToMenuView() {
        pointsCalculatorPresenter.switchToMenuView();
    }
}