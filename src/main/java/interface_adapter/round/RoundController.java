package interface_adapter.round;

import use_case.round.RoundInputBoundary;
import use_case.round.RoundInputData;
import use_case.hint.HintInputBoundary;
import use_case.hint.HintInputData;

public class RoundController {

    private final RoundInputBoundary roundUseCaseInteractor;
    private final HintInputData hintInputData;

    public RoundController(RoundInputBoundary roundUseCaseInteractor, HintInputData hintInputData) {
        this.roundUseCaseInteractor = roundUseCaseInteractor;
        this.hintInputData = hintInputData;
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


    /**
     * Executes the "switch to New Round" Use Case.
     */
    public void switchToNewRoundView() {
    }

}
