package interface_adapter.round;

import use_case.round.RoundInputBoundary;
import use_case.round.RoundInputData;

import java.util.Map;

public class RoundController {

    private final RoundInputBoundary roundUseCaseInteractor;

    public RoundController(RoundInputBoundary roundUseCaseInteractor) {
        this.roundUseCaseInteractor = roundUseCaseInteractor;
    }

    /**
     * Executes the Round Use Case.
     */
    public void execute() {
        Map<String, Object> randLocation =
                roundUseCaseInteractor.getRandLocation("src/main/resources/rand_locations.json");

        double latitude = (double) randLocation.get("latitude");
        double longitude = (double) randLocation.get("longitude");
        String country = (String) randLocation.get("country");

        final RoundInputData roundInputData = new RoundInputData(latitude,longitude, country);

        roundUseCaseInteractor.execute(roundInputData);

    }


}
