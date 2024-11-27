package use_case.hint;

import java.util.List;
import java.util.Map;

public class HintInteractor {

    private int hintCount = 0;
    private final List<String> hints;

    public HintInteractor(String filePath, HintInputData inputData) {
        Map<String, Map<String, String>> countryData = HintCountryDataLoader.loadCountryData(filePath);
        HintGenerator hintGenerator = new HintGenerator(countryData);
        String country = inputData.getCountry();

        HintOutputData outputData = hintGenerator.generateHint(country);
        hints = List.of(
                outputData.getYearOfEstablishment(),
                outputData.getOfficialLanguages(),
                outputData.getFlag()
        );
    }

    public String getNextHint() {
        if (hintCount < hints.size()) {
            return hints.get(hintCount++);
        } else {
            return "No more hints available.";
        }
    }
}