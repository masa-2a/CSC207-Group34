package interface_adapter.round;

import use_case.round.RoundInputBoundary;
import use_case.round.RoundInputData;

public class RoundController {

    private final RoundInputBoundary roundUseCaseInteractor;

    public RoundController(RoundInputBoundary roundUseCaseInteractor) {
        this.roundUseCaseInteractor = roundUseCaseInteractor;
    }

    /**
     * Executes the Round Use Case.
     */
    public void execute(int width, int height, double latitude,
                        double longitude, int zoom, double guessLat, double guessLong,
                        double answerLat, double answerLong, boolean guessed, boolean answered) {
        final RoundInputData roundInputData = new RoundInputData(width, height, latitude, longitude,
                zoom, guessLat, guessLong, answerLat, answerLong, guessed, answered);

        roundUseCaseInteractor.execute(roundInputData);

    }


}
