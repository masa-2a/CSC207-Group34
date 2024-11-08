package use_case;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class CountdownTimer {
    final private static Duration countdownDuration = Duration.ofMinutes(2).plusSeconds(30);
    private static Instant startTime;
    final private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        // Start timer
        startCountdown();

        // Simulate a "Submit" button with a console input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to 'submit' and calculate remaining time.");
        scanner.nextLine();  // NEEDS TO BE CHANGED DEPENDING ON THE LATER BUTTON IMPLEMENTATION

        // Calculate remaining time on submit
        calculateRemainingTimeOnSubmit();
        scheduler.shutdown();  // Stop the scheduler when submitted
    }

    public static void startCountdown() {
        // Record start time
        startTime = Instant.now();

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

    public static float calculateRemainingTimeOnSubmit() {
        // Calculate elapsed time since start
        Duration elapsedTime = Duration.between(startTime, Instant.now());
        // Calculate remaining time
        Duration timeRemaining = countdownDuration.minus(elapsedTime);

        if (timeRemaining.isNegative() || timeRemaining.isZero()) {
            return 0;
        } else {
            long minutes = timeRemaining.toMinutes();
            long seconds = timeRemaining.minusMinutes(minutes).getSeconds();
            return minutes*60 + seconds;
        }
    }
}
