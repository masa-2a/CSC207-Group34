package use_case.round;

import use_case.streetview_map.StreetViewMapInputBoundary;
import use_case.streetview_map.StreetViewMapInputData;
import use_case.streetview_map.StreetViewMapOutputData;

import java.util.*;

public class RoundInteractor implements RoundInputBoundary {
    private final RoundOutputBoundary roundPresenter;
    private final StreetViewMapInputBoundary streetViewMapInteractor;

    public RoundInteractor(StreetViewMapInputBoundary streetViewMapInteractor,
                           RoundOutputBoundary roundPresenter) {
        this.roundPresenter = roundPresenter;
        this.streetViewMapInteractor = streetViewMapInteractor;
    }

    @Override
    public void execute(RoundInputData roundInputData) {

        StreetViewMapInputData streetViewMapInputData = roundInputData.getStreetViewMapInputData();

        StreetViewMapOutputData streetViewMapOutputData =
                streetViewMapInteractor.execute(streetViewMapInputData);


        RoundOutputData roundOutputData = new RoundOutputData(
                streetViewMapOutputData.getUserLatitude(),
                streetViewMapOutputData.getUserLongitude());

        System.out.println("These are the guessed coords:" + roundOutputData.getGuessLat() +
                roundOutputData.getGuessLong());
        roundPresenter.presentMapData(roundOutputData);
    }

    @Override
    public Map<String, Object> getRandLocation(String jsonFilePath) {
        return RandomDataAccess(jsonFilePath);
    }

    private Map<String, Object> RandomDataAccess(String jsonFilePath) {
        RoundDataAccess roundDataAccess = new RoundDataAccess(jsonFilePath);
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
