package interface_adapter.points_calculator;

public class PointsCalculatorState {
    private int points;
    public String imagePath;

    /**
     * Getter method
     * @return user points
     */
    public int getPoints() {return points;}

    /**
     * Getter method
     * @return image path
     */
    public String getImagePath() {return imagePath;}

    /**
     * updates the points with the points earned
     * @param pointsEarned
     */
    public void updatePoints(int pointsEarned) {this.points += pointsEarned;}

    /**
     * updates the imagePath
     * @param imagePath
     */
    public void updateImagePath(String imagePath) {this.imagePath = imagePath;}
}
