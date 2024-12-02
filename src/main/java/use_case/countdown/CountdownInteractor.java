package use_case.countdown;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import use_case.round.RoundOutputBoundary;

/**
 * The Countdown Interactor.
 */
public class CountdownInteractor implements CountdownInputBoundary {
    private static final int SECONDSTOMINUTE = 60;
    private Instant startTime;
    private ScheduledExecutorService scheduler;
    private final RoundOutputBoundary roundPresenter;

    public CountdownInteractor(RoundOutputBoundary roundPresenter) {
        this.roundPresenter = roundPresenter;
    }

    @Override
    public void startCountdown(CountdownInputData countdownInputData) {
        scheduler = Executors.newScheduledThreadPool(1);
        final Duration countdownDuration = countdownInputData.getCountdownDuration();
        // Record start time
        this.startTime = Instant.now();

        // Scheduled task to display time remaining every second
        scheduler.scheduleAtFixedRate(() -> {
            final Duration elapsedTime = Duration.between(startTime, Instant.now());
            final Duration timeRemaining = countdownDuration.minus(elapsedTime);

            final CountdownOutputData countdownOutputData = new CountdownOutputData();

            if (!timeRemaining.isNegative() && !timeRemaining.isZero()) {
                final long minutes = timeRemaining.toMinutes();
                final long seconds = timeRemaining.minusMinutes(minutes).getSeconds();

                final String formattedTime = String.format("%02d:%02d", minutes, seconds);
                countdownOutputData.setTimeLeft(formattedTime);

                roundPresenter.updateCountdownTimer(countdownOutputData);

            }
            else {

                countdownOutputData.setTimeLeft("Time's up!");
                roundPresenter.updateCountdownTimer(countdownOutputData);

                scheduler.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public CountdownOutputData stopCountdown() {

        // Calculate remaining time on submit
        final double timeElapsed = calculateRemainingTimeOnSubmit();
        scheduler.shutdown();
        final CountdownOutputData countdownOutputData = new CountdownOutputData();
        countdownOutputData.setTimeElapsed(timeElapsed);

        return countdownOutputData;
    }

    @Override
    public double calculateRemainingTimeOnSubmit() {
        // Calculate elapsed time since start
        final Duration elapsedTime = Duration.between(startTime, Instant.now());
        // Calculate remaining time

        final long minutes = elapsedTime.toMinutes();
        final long seconds = elapsedTime.minusMinutes(minutes).getSeconds();
        return minutes * SECONDSTOMINUTE + seconds;
    }
}
