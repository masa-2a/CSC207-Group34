package use_case.streetview_map;

/**
 * Input Boundary for the StreetViewMap Use Case.
 */
public interface StreetViewMapInputBoundary {

    /**
     * Executes the StreetViewMap Use Case.
     *
     * @param streetViewInputData the input data
     */
    void execute(StreetViewMapInputData streetViewInputData);

    /**
     * Submits the guess.
     *
     * @return the output data
     */
    StreetViewMapOutputData guessSubmit();
}
