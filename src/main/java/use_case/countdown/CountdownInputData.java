package use_case.countdown;

import java.time.Duration;

/**
 * The input data for the Countdown Use Case.
 */
public class CountdownInputData {
    private Duration countdownDuration;

    public CountdownInputData(Duration countdownDuration) {
        this.countdownDuration = countdownDuration;
    }

    public Duration getCountdownDuration() {
        return countdownDuration;
    }

    public void setCountdownDuration(Duration countdownDuration) {
        this.countdownDuration = countdownDuration;
    }
}
