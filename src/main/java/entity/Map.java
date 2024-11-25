package entity;

public class Map {
    private double totalDistance;

    public Map(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void MapCoordinates(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getTotalDistance() {
        return totalDistance;
    }
}
