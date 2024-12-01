package use_case.streetview_map;

/**
 * Output Boundary for the StreetViewMap Use Case.
 */
public interface StreetViewMapOutputBoundary {
    /**
     * Presents the output data.
     *
     * @param outputData the output data
     */
    void present(StreetViewMapOutputData outputData);
}
