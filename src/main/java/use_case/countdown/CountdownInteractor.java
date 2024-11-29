package use_case.countdown;

import use_case.round.RoundOutputBoundary;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
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


//    CountdownInteractor countdownInteractor = new CountdownInteractor();
//
//    // Start timer
//        countdownInteractor.startCountdown();
//
//    // Simulate a "Submit" button with a console input
//    Scanner scanner = new Scanner(System.in);
//        System.out.println("Press Enter to 'submit' and calculate remaining time.");
//        scanner.nextLine();  // NEEDS TO BE CHANGED DEPENDING ON THE LATER BUTTON IMPLEMENTATION
//
//    // Calculate remaining time on submit
//        countdownInteractor.calculateRemainingTimeOnSubmit();
//        scheduler.shutdown();  // Stop the scheduler when submitted


    @Override
    public void startCountdown(CountdownInputData countdownInputData) {
        Duration countdownDuration = countdownInputData.getCountdownDuration();
        // Record start time
        this.startTime = Instant.now();

        // Scheduled task to display time remaining every second
        scheduler.scheduleAtFixedRate(() -> {
            Duration elapsedTime = Duration.between(startTime, Instant.now());
            Duration timeRemaining = countdownDuration.minus(elapsedTime);

            if (!timeRemaining.isNegative() && !timeRemaining.isZero()) {
                long minutes = timeRemaining.toMinutes();
                long seconds = timeRemaining.minusMinutes(minutes).getSeconds();
                System.out.printf("Time remaining: %02d:%02d\n", minutes, seconds);

            } else {
                System.out.println("Time's up!");
                scheduler.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public float calculateRemainingTimeOnSubmit() {
//        // Calculate elapsed time since start
//        Duration elapsedTime = Duration.between(startTime, Instant.now());
//        // Calculate remaining time
//        Duration timeRemaining = countdownDuration.minus(elapsedTime);
//
//        if (timeRemaining.isNegative() || timeRemaining.isZero()) {
//            return 0;
//        } else {
//            long minutes = timeRemaining.toMinutes();
//            long seconds = timeRemaining.minusMinutes(minutes).getSeconds();
//            return minutes*60 + seconds;
//        }
        return 0;
    }
}
