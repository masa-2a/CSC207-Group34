package interface_adapter.points_calculator;

public class PointsCalculatorState {
    private int points;
    public String imagePath;
    private String message;
    private String error;


    public int getPoints() {return points;}
    public String getImagePath() {return imagePath;}
    public void updatePoints(int points) {this.points = points;}
    public void updateMessage(String message) {this.message = message;}
    public void updateImagePath(String imagePath) {this.imagePath = imagePath;}
    public void setError(String inputError) {
        this.error = inputError;
    }
}
