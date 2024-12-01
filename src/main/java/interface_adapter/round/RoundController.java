package interface_adapter.round;

import use_case.countdown.CountdownInputBoundary;
import use_case.countdown.CountdownInputData;
import use_case.countdown.CountdownOutputData;
import use_case.hint.HintInputBoundary;
import use_case.hint.HintInputData;
import use_case.hint.HintOutputData;
import use_case.pointsCalculator.PointsCalculatorInputBoundary;
import use_case.pointsCalculator.PointsCalculatorInputData;
import use_case.round.RoundInputBoundary;
import use_case.round.RoundInputData;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RoundController {
    private final RoundInputBoundary roundUseCaseInteractor;
    private final CountdownInputBoundary countdownInteractor;
    private final PointsCalculatorInputBoundary pointsCalculatorInteractor;
    private final HintInputBoundary hintInteractor;

    public RoundController(RoundInputBoundary roundUseCaseInteractor,
                           CountdownInputBoundary countdownInteractor,
                           PointsCalculatorInputBoundary pointsCalculatorInteractor,
                           HintInputBoundary hintInteractor) {
        this.roundUseCaseInteractor = roundUseCaseInteractor;
        this.countdownInteractor = countdownInteractor;
        this.pointsCalculatorInteractor = pointsCalculatorInteractor;
        this.hintInteractor = hintInteractor;
    }

    /**
     * Executes the Round Use Case.
     */
    public void execute() {
        // Retrieve a random location
        Map<String, Object> randLocation = roundUseCaseInteractor.getRandLocation();

        double latitude = (double) randLocation.get("latitude");
        double longitude = (double) randLocation.get("longitude");
        String country = (String) randLocation.get("country");

        System.out.println("The latitude is " + latitude + " the longitude is " + longitude);

        // CountdownTimer Stuff
        CountdownInputData countdownInputData = new
                CountdownInputData(Duration.ofMinutes(2).plusSeconds(30));
        countdownInteractor.startCountdown(countdownInputData);

        // Create input data
        final RoundInputData roundInputData = new RoundInputData(latitude, longitude, country);

        // Execute the round use case
        roundUseCaseInteractor.execute(roundInputData);

    }

    public void submitGuess(Double goalLat, Double goalLong,
                            Double guessedLat, Double guessedLong, String country, int hintsUsed) {

        final RoundInputData roundInputData = new RoundInputData(goalLat, goalLong, country);

        CountdownOutputData countdownOutputData =
                countdownInteractor.stopCountdown();

        System.out.println("SUBMIT GUESS HINTS USED" + hintsUsed);
        roundInputData.setElapsedTime(countdownOutputData.getTimeElapsed());
        roundInputData.setHintsUsed(hintsUsed);

        Map<String, Double> randomLocation = new HashMap<>();
        randomLocation.put("latitude", goalLat);
        randomLocation.put("longitude", goalLong);

        Map<String, Double> chosenLocation = new HashMap<>();
        chosenLocation.put("latitude", guessedLat);
        chosenLocation.put("longitude", guessedLong);

        PointsCalculatorInputData pointsCalculatorInputData =
                new PointsCalculatorInputData(randomLocation, chosenLocation,
                        roundInputData.getElapsedTime(), hintsUsed,
                        "src/main/resources/static_map.png");

        //pointsCalculatorInteractor.execute(pointsCalculatorInputData);

        roundUseCaseInteractor.guessSubmit(roundInputData);
    }

    public void showHint(String country) {
        HintInputData hintInputData = new HintInputData(country,
                "src/main/resources/country_data.json");

        HintOutputData hint = hintInteractor.execute(hintInputData);
        roundUseCaseInteractor.showHint(hint);

    }

}
