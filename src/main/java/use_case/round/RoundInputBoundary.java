package use_case.round;

import use_case.map2d.Map2DInputData;

import java.util.Map;

/**
 * Input Boundary for Map2D stuff
 */
public interface RoundInputBoundary {

    /**
     * Executes the view 2d map use case.
     * @param roundInputData the input data
     */
    void execute(RoundInputData roundInputData);

    /**
     * Gets the random location for use in round.
     * @return An Entry of the Hashmap of the random location's long, lat and country
     */
    Map<String, Object> getRandLocation(String jsonFilePath);


}