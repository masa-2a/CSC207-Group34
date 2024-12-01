package use_case.map2d;

/**
 * Output Boundary for the Map2D Use Case.
 */
public interface Map2DOutputBoundary {
    /**
     * Prepares the success view for the Map2D Use Case.
     * @param outputData the output data
     */
    void prepareMapView(Map2DOutputData outputData);

}
