package use_case.round;

import org.jetbrains.annotations.NotNull;
import use_case.hint.HintOutputData;
import use_case.map2d.Map2DInputBoundary;
import use_case.pointsCalculator.PointsCalculatorInputBoundary;
import use_case.pointsCalculator.PointsCalculatorInputData;
import use_case.streetview_map.StreetViewMapInputBoundary;
import use_case.streetview_map.StreetViewMapInputData;
import use_case.streetview_map.StreetViewMapOutputData;

import java.util.*;

public class RoundInteractor implements RoundInputBoundary {
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

        StreetViewMapInputData streetViewMapInputData = roundInputData.getStreetViewMapInputData();

        RoundOutputData roundOutputData = getRoundOutputData(roundInputData);

        streetViewMapInteractor.execute(streetViewMapInputData);

        roundPresenter.presentMapData(roundOutputData);
    }

    @Override
    public void guessSubmit(RoundInputData roundInputData) {
        StreetViewMapOutputData streetViewMapOutputData = streetViewMapInteractor.guessSubmit();

        RoundOutputData roundOutputData = getRoundOutputData(roundInputData, streetViewMapOutputData);

        System.out.println("These are the random coords: " +
                roundInputData.getStreetViewMapInputData().getGoalLatitude() +
                "," + roundInputData.getStreetViewMapInputData().getGoalLongitude());
        System.out.println("These are the guessed coords:" + streetViewMapOutputData.getUserLatitude() + "," +
                streetViewMapOutputData.getUserLongitude());

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
                                                      StreetViewMapOutputData
                                                              streetViewMapOutputData) {
        Map<String, Double> randCoords = new HashMap<>();
        randCoords.put("latitude", roundInputData.getStreetViewMapInputData().getGoalLatitude());
        randCoords.put("longitude", roundInputData.getStreetViewMapInputData().getGoalLongitude());

        Map<String, Double> guessCoords = new HashMap<>();
        guessCoords.put("latitude", streetViewMapOutputData.getUserLatitude());
        guessCoords.put("longitude", streetViewMapOutputData.getUserLongitude());

        double elapsedTime = roundInputData.getElapsedTime();

        return new RoundOutputData(randCoords, guessCoords, roundInputData.getCountry(),
                elapsedTime,0,"src/main/resources/static_map.png");
    }

    @NotNull
    private static RoundOutputData getRoundOutputData(RoundInputData roundInputData) {
        Map<String, Double> randCoords = new HashMap<>();
        randCoords.put("latitude", roundInputData.getStreetViewMapInputData().getGoalLatitude());
        randCoords.put("longitude", roundInputData.getStreetViewMapInputData().getGoalLongitude());

        Map<String, Double> guessCoords = new HashMap<>();
        guessCoords.put("latitude", 0.);
        guessCoords.put("longitude", 0.);


        return new RoundOutputData(randCoords, guessCoords, roundInputData.getCountry(),
                0,0,"src/main/resources/static_map.png");
    }

    @Override
    public Map<String, Object> getRandLocation() {
        return RandomDataAccess();
    }



    private Map<String, Object> RandomDataAccess() {
        Map<String, Map<String, Object>> countryData = roundDataAccess.loadCountryData();

        List<Map.Entry<String, Map<String, Object>>> entries = new ArrayList<>(countryData.entrySet());

        Random random = new Random();
        Map.Entry<String, Map<String, Object>> randEntry = entries.get(random.nextInt(entries.size()));

        Map<String, Object> randLocation = new HashMap<>();
        randLocation.put("country", randEntry.getValue().get("country"));
        randLocation.put("latitude", Double.parseDouble(randEntry.getValue().get("latitude").toString()));
        randLocation.put("longitude", Double.parseDouble(randEntry.getValue().get("longitude").toString()));

        return randLocation;
    }
}

