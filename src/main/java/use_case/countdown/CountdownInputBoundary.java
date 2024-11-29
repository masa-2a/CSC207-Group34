package use_case.countdown;

public interface CountdownInputBoundary {

    void startCountdown(CountdownInputData countdownInputData);

    float calculateRemainingTimeOnSubmit();
}
