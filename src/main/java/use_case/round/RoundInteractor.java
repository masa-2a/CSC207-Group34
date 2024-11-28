package use_case.round;

import use_case.map2d.Map2DInputBoundary;
import use_case.map2d.Map2DOutputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RoundInteractor implements RoundInputBoundary {
    private final RoundOutputBoundary roundPresenter;

    private final Map2DInputBoundary map2DInteractor;

    public RoundInteractor(Map2DInputBoundary map2DInteractor, RoundOutputBoundary roundPresenter) {
        this.map2DInteractor = map2DInteractor;
        this.roundPresenter = roundPresenter;
    }

    @Override
    public void execute(RoundInputData roundInputData) {
        Map2DOutputData map2DOutputData = map2DInteractor.execute(roundInputData.getMap2DInputData());
        RoundOutputData roundOutputData = new RoundOutputData(map2DOutputData);
        roundPresenter.presentMapData(roundOutputData);
    }

    @Override
    public Map.Entry<String, Map<String, String>> getRandLocation() {
        RoundDataAccess roundDataAccess = new RoundDataAccess (
                "src/main/resources/rand_locations.json");
        roundDataAccess.loadCountryData();
        Map<String, Map<String, String>> countryData = roundDataAccess.loadCountryData();

        // Get a random entry from the map
        List<Map.Entry<String, Map<String, String>>> entries = new ArrayList<>(countryData.entrySet());
        Random random = new Random();
        return entries.get(random.nextInt(entries.size()));
    }

}
