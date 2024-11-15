package use_case.generate_hint;

import java.util.Map;

public class HintGenerator {

    private final Map<String, String> establishmentYears;
    private final Map<String, String> officialLanguages;

    public HintGenerator(Map<String, String> establishmentYears, Map<String, String> officialLanguages) {
        this.establishmentYears = establishmentYears;
        this.officialLanguages = officialLanguages;
    }

    public String getYearOfEstablishment(String country) {
        return "Year of establishment: " + establishmentYears.getOrDefault(country, "Unknown");
    }

    public String getOfficialLanguages(String country) {
        return "Official languages: " + officialLanguages.getOrDefault(country, "Unknown");
    }

    public String getFlag(String country) {
        if (country.contains("Laos")) {
            return "Flag: 🇱🇦";
        } else if (country.contains("France")) {
            return "Flag: 🇫🇷";
        } else {
            return "Flag: Unknown";
        }
    }
}