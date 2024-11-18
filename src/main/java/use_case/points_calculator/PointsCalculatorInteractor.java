package use_case.points_calculator;


import data_access.DBUserDataAccessObject;
import use_case.CountdownTimer;
import entity.PointsCalculator;
import java.util.Map;

public class PointsCalculatorInteractor implements PointsCalculatorInputBoundary{
    private final PointsCalculatorUserDataAccessInterface userDataAccessObject;
    private final PointsCalculatorOutputBoundary pointsCalculatorPresenter;

    public PointsCalculatorInteractor(PointsCalculatorUserDataAccessInterface userDataAccessInterface, PointsCalculatorOutputBoundary pointsCalculatorPresenter) {
        this.userDataAccessObject = userDataAccessInterface;
        this.pointsCalculatorPresenter = pointsCalculatorPresenter;
    }

    /*
    Calculating the distance between two points on earth,
    the random location and the chosen location
     */

@Override
public int execute(PointsCalculatorInputData pointsCalculatorInputData){
    final Map<String, Double> randomLocation = pointsCalculatorInputData.getRandomLocation();
    final Map<String, Double> chosenLocation = pointsCalculatorInputData.getChosenLocation();
    double distance = PointsCalculator.calculateDistance(randomLocation, chosenLocation);
    double timespent = pointsCalculatorInputData.getTimespent();
    //int hintsused = hintCounter();
    int points = (int) Math.floor(PointsCalculator.MAX_SCORE - distance/10 - timespent); //need to add hints later
    return points;
}


