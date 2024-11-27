package use_case.map2d;

import use_case.map2d.Map2DInputData;

/**
 * Input Boundary for Map2D stuff
 */
public interface Map2DInputBoundary {

    /**
     * Executes the view 2d map use case.
     * @param map2DInputData the input data
     */
    void execute(Map2DInputData map2DInputData);

}