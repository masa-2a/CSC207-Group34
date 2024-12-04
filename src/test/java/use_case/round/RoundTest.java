package use_case.round;

import com.google.gson.JsonParseException;
import interface_adapter.round.RoundPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.hint.HintOutputData;
import use_case.pointsCalculator.PointsCalculatorInputBoundary;
import use_case.pointsCalculator.PointsCalculatorInputData;
import use_case.streetview_map.StreetViewMapInputData;
import use_case.streetview_map.StreetViewMapInputBoundary;
import use_case.streetview_map.StreetViewMapOutputData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RoundTest {
    private RoundInputBoundary roundInteractor;
    private StreetViewMapInputBoundary streetViewMapInteractor;
    private RoundOutputBoundary roundPresenter;
    private RoundDataAccessInterface roundDataAccess;
    private PointsCalculatorInputBoundary pointsCalculatorInteractor;

    @BeforeEach
    void setUp() {
        streetViewMapInteractor = mock(StreetViewMapInputBoundary.class);
        roundPresenter = mock(RoundPresenter.class);
        roundDataAccess = mock(RoundDataAccess.class);
        pointsCalculatorInteractor = mock(PointsCalculatorInputBoundary.class);
        roundInteractor = new RoundInteractor(streetViewMapInteractor, roundPresenter, roundDataAccess, pointsCalculatorInteractor);
    }

    @Test
    void testExecute() {
        RoundInputData roundInputData = new RoundInputData(0, 0, "country");
        roundInputData.setElapsedTime(10);
        roundInputData.setHintsUsed(1);
        StreetViewMapInputData streetViewMapInputData = new StreetViewMapInputData(
                roundInputData.getStreetViewMapInputData().getGoalLatitude(),
                roundInputData.getStreetViewMapInputData().getGoalLongitude());

        roundInteractor.execute(roundInputData);

        verify(streetViewMapInteractor).execute(argThat(argument ->
                argument.getGoalLatitude() == streetViewMapInputData.getGoalLatitude() &&
                        argument.getGoalLongitude() == streetViewMapInputData.getGoalLongitude()
        ));
        verify(roundPresenter).presentMapData(any(RoundOutputData.class));
    }

    @Test
    void testGuessSubmit() {
        RoundInputData roundInputData = new RoundInputData(0, 0,"country");
        StreetViewMapOutputData streetViewMapOutputData = new StreetViewMapOutputData(0, 0);

        when(streetViewMapInteractor.guessSubmit()).thenReturn(streetViewMapOutputData);

        roundInteractor.guessSubmit(roundInputData);

        verify(pointsCalculatorInteractor).execute(any(PointsCalculatorInputData.class));
        verify(roundPresenter).switchToPointsCalculator(any(RoundOutputData.class));
    }

    @Test
    void testShowHint() {
        HintOutputData hint = new HintOutputData("hint", 0);

        roundInteractor.showHint(hint);

        verify(roundPresenter).updateHints(hint);
    }

    @Test
    void testGetRandLocation() {
        // Prepare mock data
        Map<String, Object> country1 = new HashMap<>();
        country1.put("country", "CountryA");
        country1.put("latitude", 12.34);
        country1.put("longitude", 56.78);

        Map<String, Object> country2 = new HashMap<>();
        country2.put("country", "CountryB");
        country2.put("latitude", 98.76);
        country2.put("longitude", 54.32);

        Map<String, Map<String, Object>> mockCountryData = new HashMap<>();
        mockCountryData.put("countryA", country1);
        mockCountryData.put("countryB", country2);

        // Mock the method loadCountryData
        when(roundDataAccess.loadCountryData()).thenReturn(mockCountryData);

        // Call the method
        Map<String, Object> result = roundInteractor.getRandLocation();

        // Verify result contains the expected keys and values
        assertTrue(result.containsKey("country"));
        assertTrue(result.containsKey("latitude"));
        assertTrue(result.containsKey("longitude"));

        // Verify that the mocked method was called
        verify(roundDataAccess).loadCountryData();
    }

    @Test
    void testRoundDataAccess() {
        // Create a new RoundDataAccess object with the file path
        RoundDataAccess roundDataAccessTest = new RoundDataAccess("src/main/resources/rand_locations.json");

        roundInteractor = new RoundInteractor(streetViewMapInteractor, roundPresenter, roundDataAccessTest, pointsCalculatorInteractor);

        // Call the method through roundInteractor
        Map<String, Object> result = roundInteractor.getRandLocation();

        // Verify result contains the expected keys and values
        assertTrue(result.containsKey("country"));
        assertTrue(result.containsKey("latitude"));
        assertTrue(result.containsKey("longitude"));
    }

    @Test
    void testLoadCountryDataWithFaultyFile() {
        // Redirect the error output stream
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err; // Save original System.err
        System.setErr(new PrintStream(errContent));

        // Create a RoundDataAccess object with a faulty JSON file path
        RoundDataAccess roundDataAccessTest = new RoundDataAccess("src/test/resources/faulty_rand_locations.json");

        // Instantiate the RoundInteractor
        roundInteractor = new RoundInteractor(streetViewMapInteractor, roundPresenter, roundDataAccessTest, pointsCalculatorInteractor);

        // Expect a RuntimeException due to the faulty JSON
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> {
            roundInteractor.getRandLocation();
        });

        // Verify the error output and exception message
        String expectedErrorMessage = "An error occurred while reading the file";
        assertTrue("Error message should match expected output.", errContent.toString().contains(expectedErrorMessage));
        assertNotNull(thrownException.getCause());
        // Reset the error output stream
        System.setErr(originalErr);
    }
}