package use_case.round;

import java.util.Map;

import use_case.hint.HintOutputData;

/**
 * Input Boundary for Map2D stuff.
 */
public interface RoundInputBoundary {

    /**
     * Executes the view 2d map use case.
     *
     * @param roundInputData the input data
     */
    void execute(RoundInputData roundInputData);

    /**
     * Submits the user's guess.
     *
     * @param roundInputData the input data
     */
    void guessSubmit(RoundInputData roundInputData);

    /**
     * Shows the hint.
     *
     * @param hint the hint
     */
    void showHint(HintOutputData hint);

    /**
     * Gets the random location for use in round.
     *
     * @return An Entry of the Hashmap of the random location's long, lat and country
     */
    Map<String, Object> getRandLocation();
}
