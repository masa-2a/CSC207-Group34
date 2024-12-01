package interface_adapter.round;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import use_case.countdown.CountdownInputBoundary;
import use_case.countdown.CountdownInputData;
import use_case.countdown.CountdownOutputData;
import use_case.hint.HintInputBoundary;
import use_case.hint.HintInputData;
import use_case.hint.HintOutputData;
import use_case.pointsCalculator.PointsCalculatorInputBoundary;
import use_case.round.RoundInputBoundary;
import use_case.round.RoundInputData;

/**
 * Controller for the Round Use Case.
 */
public class RoundController {
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";

    private final RoundInputBoundary roundUseCaseInteractor;
    private final CountdownInputBoundary countdownInteractor;
    private final HintInputBoundary hintInteractor;

    public RoundController(RoundInputBoundary roundUseCaseInteractor,
                           CountdownInputBoundary countdownInteractor,
                           HintInputBoundary hintInteractor) {
        this.roundUseCaseInteractor = roundUseCaseInteractor;
        this.countdownInteractor = countdownInteractor;
        this.hintInteractor = hintInteractor;
    }

    /**
     * Executes the Round Use Case.
     */
    public void execute() {
        // Retrieve a random location
        final Map<String, Object> randLocation = roundUseCaseInteractor.getRandLocation();

        final double latitude = (double) randLocation.get(LATITUDE);
        final double longitude = (double) randLocation.get(LONGITUDE);
        final String country = (String) randLocation.get("country");

        System.out.println("The latitude is " + latitude + " the longitude is " + longitude);

        // CountdownTimer Stuff
        final CountdownInputData countdownInputData = new
                CountdownInputData(Duration.ofMinutes(2).plusSeconds(30));
        countdownInteractor.startCountdown(countdownInputData);

        // Create input data
        final RoundInputData roundInputData = new RoundInputData(latitude, longitude, country);

        // Execute the round use case
        roundUseCaseInteractor.execute(roundInputData);
    }

    /**
     * Submits the user's guess.
     *
     * @param goalLat the latitude of the goal location
     * @param goalLong the longitude of the goal location
     * @param guessedLat the latitude of the guessed location
     * @param guessedLong the longitude of the guessed location
     * @param country the country of the goal location
     * @param hintsUsed the number of hints used
     */
    public void submitGuess(Double goalLat, Double goalLong,
                            Double guessedLat, Double guessedLong, String country, int hintsUsed) {

        final RoundInputData roundInputData = new RoundInputData(goalLat, goalLong, country);

        final CountdownOutputData countdownOutputData =
                countdownInteractor.stopCountdown();

        System.out.println("SUBMIT GUESS HINTS USED" + hintsUsed);
        roundInputData.setElapsedTime(countdownOutputData.getTimeElapsed());
        roundInputData.setHintsUsed(hintsUsed);

        final Map<String, Double> randomLocation = new HashMap<>();
        randomLocation.put(LATITUDE, goalLat);
        randomLocation.put(LONGITUDE, goalLong);

        final Map<String, Double> chosenLocation = new HashMap<>();
        chosenLocation.put(LATITUDE, guessedLat);
        chosenLocation.put(LONGITUDE, guessedLong);

        // pointsCalculatorInteractor.execute(pointsCalculatorInputData);

        roundUseCaseInteractor.guessSubmit(roundInputData);
    }

    /**
     * Shows a hint to the user.
     *
     * @param country the country of the goal location
     */
    public void showHint(String country) {
        final HintInputData hintInputData = new HintInputData(country,
                "src/main/resources/country_data.json");

        final HintOutputData hint = hintInteractor.execute(hintInputData);
        roundUseCaseInteractor.showHint(hint);

    }
}
