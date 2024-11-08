package use_case.points_calculator;

import data_access.DBUserDataAccessObject;
import use_case.CountdownTimer;

import java.util.Map;

public class PointsCalculatorInteractor {
private final Map<String, Double> randomLocation;
private final Map<String, Double> chosenLocation;

public PointsCalculatorInteractor(Map<String, Double> randomLocation, Map<String, Double> chosenLocation) {
    this.randomLocation = randomLocation;
    this.chosenLocation = chosenLocation;
}

public int calculateDistance(){
    double lat1 = Math.toRadians(this.randomLocation.get("latitude"));
    double lon1 = Math.toRadians(this.randomLocation.get("longitude"));
    double lat2 = Math.toRadians(this.chosenLocation.get("latitude"));
    double lon2 = Math.toRadians(this.chosenLocation.get("longitude"));

    double dLat = lat2 - lat1;
    double dLon = lon2 - lon1;

    // Haversine formula
    double a = Math.pow(Math.sin(dLat / 2), 2) +
            Math.cos(lat1) * Math.cos(lat2) *
                    Math.pow(Math.sin(dLon / 2), 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    final double Radius = 6371;

    // Distance in kilometers
    double distance = Radius * c;

    // Return distance as an integer (rounded)
    return (int) Math.round(distance);
}

public double calculatepoints(){
    double distance = calculateDistance();
    final int MAX_SCORE = 6000; // have to change depending on points used and timer?
    //max distance btwn 2 points on earth is 20000km (divided by 10)
    // each hint costs 1000 points - we can change later depending on the hints?
    double timespent = 150 - CountdownTimer.calculateRemainingTimeOnSubmit();
    double points = MAX_SCORE - distance/10 - timespent; //need to add hints later
    return points;
}

}
