package use_case.points_calculator;

import data_access.DBUserDataAccessObject;
import use_case.CountdownTimer;

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
    double distance = calculateDistance(randomLocation, chosenLocation);
    final int MAX_SCORE = 6000; // have to change depending on points used and timer?
    //max distance btwn 2 points on earth is 20000km (divided by 10)
    // each hint costs 1000 points - we can change later depending on the hints?
    double timespent = 150 - CountdownTimer.calculateRemainingTimeOnSubmit();
    //int hintsused = hintCounter();
    int points = (int) Math.floor(MAX_SCORE - distance/10 - timespent); //need to add hints later
    return points;
}

public double calculateDistance(Map<String, Double> randomLocation, Map<String, Double> chosenLocation ){
        double lat1 = Math.toRadians(randomLocation.get("latitude"));
        double lon1 = Math.toRadians(randomLocation.get("longitude"));
        double lat2 = Math.toRadians(chosenLocation.get("latitude"));
        double lon2 = Math.toRadians(chosenLocation.get("longitude"));

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Haversine formula to calculate the distance between two points on a sphere
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        final double Radius = 6371;
        double distance = Radius * c;

        // Return distance as an integer (rounded)
        return (int) Math.round(distance);
    }

}
