package use_case.countdown;

/**
 * The output data for the Countdown Use Case.
 */
public class CountdownOutputData {
    private double timeElapsed;
    private String timeLeft;

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(double timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }
}
