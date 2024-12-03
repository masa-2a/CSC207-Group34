package use_case.round;

import interface_adapter.round.RoundPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.hint.HintOutputData;
import use_case.pointsCalculator.PointsCalculatorInputBoundary;
import use_case.pointsCalculator.PointsCalculatorInputData;
import use_case.streetview_map.StreetViewMapInputData;
import use_case.streetview_map.StreetViewMapInputBoundary;
import use_case.streetview_map.StreetViewMapOutputData;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RoundTest {
    private RoundInteractor roundInteractor;
    private StreetViewMapInputBoundary streetViewMapInteractor;
    private RoundPresenter roundPresenter;
    private RoundDataAccess roundDataAccess;
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
        Map<String, Object> expectedLocation = new HashMap<>();
        expectedLocation.put("country", "country");
        expectedLocation.put("latitude", 0.0);
        expectedLocation.put("longitude", 0.0);

        when(roundDataAccess.loadCountryData()).thenReturn(Map.of("country", expectedLocation));

        Map<String, Object> randLocation = roundInteractor.getRandLocation();

        assertEquals(expectedLocation, randLocation);
    }
}