package use_case.round;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jetbrains.annotations.NotNull;

import use_case.hint.HintOutputData;
import use_case.map2d.Map2DInputBoundary;
import use_case.pointsCalculator.PointsCalculatorInputBoundary;
import use_case.pointsCalculator.PointsCalculatorInputData;
import use_case.streetview_map.StreetViewMapInputBoundary;
import use_case.streetview_map.StreetViewMapInputData;
import use_case.streetview_map.StreetViewMapOutputData;

/**
 * Interactor for the Round Use Case.
 */
public class RoundInteractor implements RoundInputBoundary {
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";

    private final RoundOutputBoundary roundPresenter;
    private final StreetViewMapInputBoundary streetViewMapInteractor;
    private final RoundDataAccessInterface roundDataAccess;
    private final PointsCalculatorInputBoundary pointsCalculatorInteractor;

    public RoundInteractor(StreetViewMapInputBoundary streetViewMapInteractor,
                           RoundOutputBoundary roundPresenter,
                           RoundDataAccessInterface roundDataAccess,
                           PointsCalculatorInputBoundary pointsCalculatorInteractor) {
        this.roundPresenter = roundPresenter;
        this.streetViewMapInteractor = streetViewMapInteractor;
        this.roundDataAccess = roundDataAccess;
        this.pointsCalculatorInteractor = pointsCalculatorInteractor;
    }

    @Override
    public void execute(RoundInputData roundInputData) {
        final StreetViewMapInputData streetViewMapInputData = roundInputData.getStreetViewMapInputData();

        final RoundOutputData roundOutputData = getRoundOutputData(roundInputData);

        streetViewMapInteractor.execute(streetViewMapInputData);

        roundPresenter.presentMapData(roundOutputData);
    }

    @Override
    public void guessSubmit(RoundInputData roundInputData) {
        final StreetViewMapOutputData streetViewMapOutputData = streetViewMapInteractor.guessSubmit();

        final RoundOutputData roundOutputData = getRoundOutputData(roundInputData, streetViewMapOutputData);
        System.out.println("Round interactor" + roundInputData.getHintsUsed());
        roundOutputData.setHintsused(roundInputData.getHintsUsed());
        System.out.println("Round interactor" + roundOutputData.getTimespent());

        System.out.println("These are the random coords: "
                + roundInputData.getStreetViewMapInputData().getGoalLatitude()
                + "," + roundInputData.getStreetViewMapInputData().getGoalLongitude());
        System.out.println("These are the guessed coords:" + streetViewMapOutputData.getUserLatitude() + ","
                + streetViewMapOutputData.getUserLongitude());

        final PointsCalculatorInputData inputData = new PointsCalculatorInputData(
                roundOutputData.getRandomLocation(), roundOutputData.getChosenLocation(),
                roundOutputData.getTimespent(), roundOutputData.getHintsused(),
                roundOutputData.getImagepath());
        pointsCalculatorInteractor.execute(inputData);

        roundPresenter.switchToPointsCalculator(roundOutputData);
    }

    @Override
    public void showHint(HintOutputData hint) {
        roundPresenter.updateHints(hint);
    }

    @NotNull
    private static RoundOutputData getRoundOutputData(RoundInputData roundInputData,
                                                      StreetViewMapOutputData streetViewMapOutputData) {
        final Map<String, Double> randCoords = new HashMap<>();
        randCoords.put(LATITUDE, roundInputData.getStreetViewMapInputData().getGoalLatitude());
        randCoords.put(LONGITUDE, roundInputData.getStreetViewMapInputData().getGoalLongitude());

        final Map<String, Double> guessCoords = new HashMap<>();
        guessCoords.put(LATITUDE, streetViewMapOutputData.getUserLatitude());
        guessCoords.put(LONGITUDE, streetViewMapOutputData.getUserLongitude());

        final double elapsedTime = roundInputData.getElapsedTime();

        return new RoundOutputData(randCoords, guessCoords, roundInputData.getCountry(),
                elapsedTime, 0, "src/main/resources/static_map.png");
    }

    @NotNull
    private static RoundOutputData getRoundOutputData(RoundInputData roundInputData) {
        final Map<String, Double> randCoords = new HashMap<>();
        randCoords.put(LATITUDE, roundInputData.getStreetViewMapInputData().getGoalLatitude());
        randCoords.put(LONGITUDE, roundInputData.getStreetViewMapInputData().getGoalLongitude());

        final Map<String, Double> guessCoords = new HashMap<>();
        guessCoords.put(LATITUDE, 0.);
        guessCoords.put(LONGITUDE, 0.);

        return new RoundOutputData(randCoords, guessCoords, roundInputData.getCountry(),
                roundInputData.getElapsedTime(), roundInputData.getHintsUsed(), "src/main/resources/static_map.png");
    }

    @Override
    public Map<String, Object> getRandLocation() {
        return randomDataAccess();
    }

    private Map<String, Object> randomDataAccess() {
        final Map<String, Map<String, Object>> countryData = roundDataAccess.loadCountryData();

        final List<Map.Entry<String, Map<String, Object>>> entries = new ArrayList<>(countryData.entrySet());

        final Random random = new Random();
        final Map.Entry<String, Map<String, Object>> randEntry = entries.get(random.nextInt(entries.size()));

        final Map<String, Object> randLocation = new HashMap<>();
        randLocation.put("country", randEntry.getValue().get("country"));
        randLocation.put(LATITUDE, Double.parseDouble(randEntry.getValue().get(LATITUDE).toString()));
        randLocation.put(LONGITUDE, Double.parseDouble(randEntry.getValue().get(LONGITUDE).toString()));

        return randLocation;
    }
}
