package use_case.points_calculator;

import java.util.Map;

public class calculateDistance {

    public static double getDistance(Map<String, Double> randomLocation, Map<String, Double> chosenLocation) {
        if (!randomLocation.containsKey("latitude") || !randomLocation.containsKey("longitude") || !chosenLocation.containsKey("latitude") ||
                !chosenLocation.containsKey("longitude")) {
            throw new IllegalArgumentException("Random location and Chosen location must contain 'latitude' and 'longitude' keys.");
        }


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

        // Return distance
        return distance;
    }
    }
