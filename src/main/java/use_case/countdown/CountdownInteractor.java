package use_case.countdown;

import use_case.round.RoundOutputBoundary;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountdownInteractor implements CountdownInputBoundary{
    private Instant startTime;
    final private ScheduledExecutorService scheduler;
    private final RoundOutputBoundary roundPresenter;

    public CountdownInteractor(RoundOutputBoundary roundPresenter) {
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.roundPresenter = roundPresenter;
    }


    @Override
    public void startCountdown(CountdownInputData countdownInputData) {
        Duration countdownDuration = countdownInputData.getCountdownDuration();
        // Record start time
        this.startTime = Instant.now();

        // Scheduled task to display time remaining every second
        scheduler.scheduleAtFixedRate(() -> {
            Duration elapsedTime = Duration.between(startTime, Instant.now());
            Duration timeRemaining = countdownDuration.minus(elapsedTime);

            CountdownOutputData countdownOutputData = new CountdownOutputData();

            if (!timeRemaining.isNegative() && !timeRemaining.isZero()) {
                long minutes = timeRemaining.toMinutes();
                long seconds = timeRemaining.minusMinutes(minutes).getSeconds();

                String formattedTime = String.format("%02d:%02d", minutes, seconds);
                countdownOutputData.setTimeLeft(formattedTime);

                roundPresenter.updateCountdownTimer(countdownOutputData);

            } else {

                countdownOutputData.setTimeLeft("Time's up!");
                roundPresenter.updateCountdownTimer(countdownOutputData);

                scheduler.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public CountdownOutputData stopCountdown() {

        // Calculate remaining time on submit
        Double timeElapsed = calculateRemainingTimeOnSubmit();
        scheduler.shutdown(); // Stop the scheduler when submitted
        CountdownOutputData countdownOutputData = new CountdownOutputData();
        countdownOutputData.setTimeElapsed(timeElapsed);

        return countdownOutputData;
    }

    @Override
    public double calculateRemainingTimeOnSubmit() {
        // Calculate elapsed time since start
        Duration elapsedTime = Duration.between(startTime, Instant.now());
        // Calculate remaining time

        long minutes = elapsedTime.toMinutes();
        long seconds = elapsedTime.minusMinutes(minutes).getSeconds();
        return minutes*60 + seconds;
    }
}
