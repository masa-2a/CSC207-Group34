package entity;

import java.util.Map;

public class CountryData {
    private final String yearOfEstablishment;
    private final String officialLanguages;
    private final String flag;

    public CountryData(Map<String, String> attributes) {
        this.yearOfEstablishment = attributes.getOrDefault("year", "Unknown");
        this.officialLanguages = attributes.getOrDefault("languages", "Unknown");
        this.flag = attributes.getOrDefault("flag", "Unknown");
    }

    public String getYearOfEstablishment() {
        return yearOfEstablishment;
    }

    public String getOfficialLanguages() {
        return officialLanguages;
    }

    public String getFlag() {
        return flag;
    }
}