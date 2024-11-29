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
        // Retrieve a random location
        Map<String, Object> randLocation = roundUseCaseInteractor.getRandLocation();

        double latitude = (double) randLocation.get("latitude");
        double longitude = (double) randLocation.get("longitude");
        String country = (String) randLocation.get("country");

        System.out.println("The latitude is " + latitude + " the longitude is " + longitude);

        // Create input data
        final RoundInputData roundInputData = new RoundInputData(latitude, longitude, country);

        // Execute the round use case
        roundUseCaseInteractor.execute(roundInputData);
    }
}
