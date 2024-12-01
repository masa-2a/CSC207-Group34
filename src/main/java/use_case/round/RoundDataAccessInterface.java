package use_case.round;

import java.util.Map;

/**
 * Data Access Interface for the Round Use Case.
 */
public interface RoundDataAccessInterface {

    /**
     * Loads the country data.
     * @return the country data
     */
    Map<String, Map<String, Object>> loadCountryData();

}

