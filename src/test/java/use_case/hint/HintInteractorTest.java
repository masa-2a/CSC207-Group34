package use_case.hint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HintInteractorTest {

    @Test
    void successTest() {
        // Create valid input data
        HintInputData inputData = new HintInputData("Albania");

        HintOutputBoundary successPresenter = new HintOutputBoundary() {
            @Override
            public void prepareSuccessView(HintOutputData outputData) {
                // Verify correct hints are generated
                assertEquals("1912", outputData.getYearOfEstablishment());
                assertEquals("Albanian", outputData.getOfficialLanguages());
                assertEquals("🇦🇱", outputData.getFlag());
                System.out.println("Success: " + outputData);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Success scenario should not invoke failure presenter.");
            }
        };

        // Create interactor and execute
        HintInputBoundary interactor = new HintInteractor(inputData, successPresenter);
        interactor.execute(inputData);

        // Verify hints can be retrieved in sequence
        assertEquals("Hint 1: 1912", ((HintInteractor) interactor).getNextHint());
        assertEquals("Hint 2: Albanian", ((HintInteractor) interactor).getNextHint());
        assertEquals("Hint 3: 🇦🇱", ((HintInteractor) interactor).getNextHint());
        assertEquals("No more hints available.", ((HintInteractor) interactor).getNextHint());
    }

    @Test
    void failTest1_nullCountry() {
        // Create input data with null country
        HintInputData inputData = new HintInputData(null);

        HintOutputBoundary failurePresenter = new HintOutputBoundary() {
            @Override
            public void prepareSuccessView(HintOutputData outputData) {
                fail("Failure scenario should not invoke success presenter.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // Verify appropriate failure message is shown
                assertEquals("Invalid country input.", errorMessage);
                System.out.println("Failure: " + errorMessage);
            }
        };

        // Create interactor and execute
        HintInputBoundary interactor = new HintInteractor(inputData, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failTest2_countryNotInData() {
        // Create input data with a country not in the JSON
        HintInputData inputData = new HintInputData("Atlantis");

        HintOutputBoundary failurePresenter = new HintOutputBoundary() {
            @Override
            public void prepareSuccessView(HintOutputData outputData) {
                fail("Failure scenario should not invoke success presenter.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // Verify appropriate failure message is shown
                assertEquals("Country not found in data.", errorMessage);
                System.out.println("Failure: " + errorMessage);
            }
        };

        // Create interactor and execute
        HintInputBoundary interactor = new HintInteractor(inputData, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failTest3_partialData() {

        HintInputData inputData = new HintInputData("Albania");

        HintOutputBoundary failurePresenter = new HintOutputBoundary() {
            @Override
            public void prepareSuccessView(HintOutputData outputData) {
                // Verify partial data is handled gracefully
                assertEquals("1912", outputData.getYearOfEstablishment());
                assertEquals("Albanian", outputData.getOfficialLanguages());
                assertEquals("Unknown", outputData.getFlag());
                System.out.println("Partial data handled: " + outputData);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Partial data scenario should not invoke failure presenter.");
            }
        };

        // Create interactor and execute
        HintInputBoundary interactor = new HintInteractor(inputData, failurePresenter);
        interactor.execute(inputData);
    }

}
