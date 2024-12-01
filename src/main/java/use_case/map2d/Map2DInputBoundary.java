package use_case.map2d;

/**
 * Input Boundary for Map2D stuff.
 */
public interface Map2DInputBoundary {

    /**
     * Executes the view 2d map use case.
     * @param map2DInputData the input data
     * @return the output data
     */
    Map2DOutputData execute(Map2DInputData map2DInputData);

}
