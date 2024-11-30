package use_case.countdown;

import java.time.Duration;

public interface CountdownInputBoundary {

    void startCountdown(CountdownInputData countdownInputData);

    CountdownOutputData stopCountdown();

    double calculateRemainingTimeOnSubmit();
}
