package use_case.hint;

import java.util.HashMap;
import java.util.Map;

public class HintGenerator {

    private final Map<String, String> establishmentYears;
    private final Map<String, String> officialLanguages;
    private final Map<String, String> countryFlags;

    private static final String UNKNOWN_YEAR = "Year of establishment: Unknown";
    private static final String UNKNOWN_LANGUAGES = "Official languages: Unknown";
    private static final String UNKNOWN_FLAG = "Flag: Unknown";

    public HintGenerator(Map<String, Map<String, String>> countryData) {
        this.establishmentYears = extractData(countryData, "year_of_establishment");
        this.officialLanguages = extractData(countryData, "official_languages");
        this.countryFlags = extractData(countryData, "flag");
    }

    public HintOutputData generateHint(String country) {
        return new HintOutputData(
                getYearOfEstablishment(country),
                getOfficialLanguages(country),
                getFlag(country)
        );
    }

    private Map<String, String> extractData(Map<String, Map<String, String>> countryData, String key) {
        Map<String, String> data = new HashMap<>();
        for (Map.Entry<String, Map<String, String>> entry : countryData.entrySet()) {
            String country = entry.getKey();
            String value = entry.getValue().getOrDefault(key, "Unknown");
            data.put(country, value);
        }
        return data;
    }

    private String getYearOfEstablishment(String country) {
        if (country == null || country.isEmpty()) {
            return UNKNOWN_YEAR;
        }
        return establishmentYears.getOrDefault(country, "Unknown");
    }

    private String getOfficialLanguages(String country) {
        if (country == null || country.isEmpty()) {
            return UNKNOWN_LANGUAGES;
        }
        return officialLanguages.getOrDefault(country, "Unknown");
    }

    private String getFlag(String country) {
        if (country == null || country.isEmpty()) {
            return UNKNOWN_FLAG;
        }
        return countryFlags.getOrDefault(country, "Unknown");
    }
}