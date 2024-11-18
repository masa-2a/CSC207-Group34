package use_case.hint;

/**
 * This class contains the output data for the hint generation, which includes the hint details.
 */
public class HintOutputData {
    private final String yearOfEstablishment;
    private final String officialLanguages;
    private final String flag;

    public HintOutputData(String yearOfEstablishment, String officialLanguages, String flag) {
        this.yearOfEstablishment = yearOfEstablishment;
        this.officialLanguages = officialLanguages;
        this.flag = flag;
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