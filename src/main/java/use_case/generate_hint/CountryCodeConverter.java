package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides the service of converting country codes to their names.
 */
public class CountryCodeConverter {

    public static final int MAXNO = 3;
    public static final int TOTALNO = 4;
    private Map<String, String> codes = new HashMap<String, String>();

    /**
     * Default constructor which will load the country codes from "country-codes.txt"
     * in the resources folder.
     */
    public CountryCodeConverter() {
        this("country-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the country code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public CountryCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));

            for (String line : lines) {
                // Skip empty or header lines
                if (line.trim().isEmpty() || line.startsWith("Country")) {
                    continue;
                }
                // Split by any whitespace to get country name and codes
                String[] parts = line.trim().split("\\s+");
                if (parts.length < TOTALNO) {
                    // Assuming parts[0] is country name and parts[2] is 3-letter country code
                    continue;
                }
                String countryname = String.join(" ", Arrays.copyOf(parts, parts.length - MAXNO)).trim();
                String twolettercode = parts[parts.length - MAXNO].trim();
                String threelettercode = parts[parts.length - 2].trim();
                String numericCode = parts[parts.length - 1].trim();
                codes.put(threelettercode, countryname);
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Returns the name of the country for the given country code.
     * @param code the 3-letter code of the country
     * @return the name of the country corresponding to the code
     */
    public String fromCountryCode(String code) {
        return codes.get(code.trim().toUpperCase());
    }

    /**
     * Returns the code of the country for the given country name.
     * @param country the name of the country
     * @return the 3-letter code of the country
     */
    public String fromCountry(String country) {
        return null;
    }

    /**
     * Returns how many countries are included in this code converter.
     * @return how many countries are included in this code converter.
     */
    public int getNumCountries() {
        return codes.size();
    }
}
