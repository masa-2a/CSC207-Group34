package use_case.hint;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HintInteractorTest {
    private HintInteractor hintInteractor;
    private final String filePath = "test_country_data.json";

    @BeforeEach
    public void setUp() throws IOException {
        // Prepare sample JSON data for tests
        JSONObject jsonData = new JSONObject();
        jsonData.put("USA", new JSONObject()
                .put("year_of_establishment", "1776")
                .put("official_languages", "English")
                .put("flag", "🇺🇸")
        );
        jsonData.put("France", new JSONObject()
                .put("year_of_establishment", "1792")
                .put("official_languages", "French")
                .put("flag", "🇫🇷")
        );
        jsonData.put("Japan", new JSONObject()
                .put("year_of_establishment", "660 BC")
                .put("official_languages", "Japanese")
                .put("flag", "🇯🇵")
        );
        jsonData.put("UnknownCountry", new JSONObject()
                .put("year_of_establishment", "")
                .put("official_languages", "")
                .put("flag", "")
        );

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(jsonData.toString().getBytes(StandardCharsets.UTF_8));
        }

        hintInteractor = new HintInteractor();
    }

    // SUCCESS TESTS

    @Test
    public void successTest01() {
        HintInputData inputData = new HintInputData("USA", filePath);

        HintOutputBoundary successPresenter = new HintOutputBoundary() {
            @Override
            public void prepareSuccessView(HintOutputData outputData) {
                List<String> expectedHints = List.of(
                        "Year of Establishment: 1776",
                        "Official Languages: English",
                        "Flag: 🇺🇸"
                );

                assertTrue(expectedHints.contains(outputData.getHint()));
                System.out.println("Hint: " + outputData.getHint());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case success is expected, failure is unexpected.");
            }

            @Override
            public void switchToMenuView() {

            }
        };

        hintInteractor = new HintInteractor();
        hintInteractor.execute(inputData); // Hint 1
        hintInteractor.execute(inputData); // Hint 2
        hintInteractor.execute(inputData); // Hint 3
    }

    @Test
    public void successTest02() {
        HintInputData inputData = new HintInputData("France", filePath);

        HintOutputBoundary successPresenter = new HintOutputBoundary() {
            @Override
            public void prepareSuccessView(HintOutputData outputData) {
                List<String> expectedHints = List.of(
                        "Year of Establishment: 1792",
                        "Official Languages: French",
                        "Flag: 🇫🇷"
                );

                assertTrue(expectedHints.contains(outputData.getHint()));
                System.out.println("Hint: " + outputData.getHint());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case success is expected, failure is unexpected.");
            }

            @Override
            public void switchToMenuView() {

            }
        };

        hintInteractor = new HintInteractor();
        hintInteractor.execute(inputData); // Hint 1
        hintInteractor.execute(inputData); // Hint 2
        hintInteractor.execute(inputData); // Hint 3
    }

    @Test
    public void successTest03() {
        HintInputData inputData = new HintInputData("Japan", filePath);

        HintOutputBoundary successPresenter = new HintOutputBoundary() {
            @Override
            public void prepareSuccessView(HintOutputData outputData) {
                List<String> expectedHints = List.of(
                        "Year of Establishment: 660 BC",
                        "Official Languages: Japanese",
                        "Flag: 🇯🇵"
                );

                assertTrue(expectedHints.contains(outputData.getHint()));
                System.out.println("Hint: " + outputData.getHint());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case success is expected, failure is unexpected.");
            }

            @Override
            public void switchToMenuView() {

            }
        };

        hintInteractor = new HintInteractor();
        hintInteractor.execute(inputData); // Hint 1
        hintInteractor.execute(inputData); // Hint 2
        hintInteractor.execute(inputData); // Hint 3
    }

    @Test
    public void successTest04_AllHintsExhausted() {
        HintInputData inputData = new HintInputData("USA", filePath);

        HintOutputBoundary successPresenter = new HintOutputBoundary() {
            @Override
            public void prepareSuccessView(HintOutputData outputData) {
                System.out.println("Hint: " + outputData.getHint());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case success is expected, failure is unexpected.");
            }

            @Override
            public void switchToMenuView() {

            }
        };

        hintInteractor = new HintInteractor();
        hintInteractor.execute(inputData); // Hint 1
        hintInteractor.execute(inputData); // Hint 2
        hintInteractor.execute(inputData); // Hint 3
        HintOutputData outputData = hintInteractor.execute(inputData); // Should indicate no more hints available

        assertEquals("No more hints available.", outputData.getHint());
    }

    // FAILURE TESTS

    @Test
    public void failureTest01_InvalidCountry() {
        HintInputData invalidInput = new HintInputData("NonExistentCountry", filePath);

        HintOutputBoundary failurePresenter = new HintOutputBoundary() {
            @Override
            public void prepareSuccessView(HintOutputData outputData) {
                fail("Success is unexpected for an invalid country.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Country data not found for: NonExistentCountry", errorMessage);
                System.out.println("Error Message: " + errorMessage);
            }

            @Override
            public void switchToMenuView() {

            }
        };

        try {
            hintInteractor.execute(invalidInput);
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
    }

    @Test
    public void failureTest02_EmptyFilePath() {
        HintInputData emptyPathInput = new HintInputData("USA", "");

        HintOutputBoundary failurePresenter = new HintOutputBoundary() {
            @Override
            public void prepareSuccessView(HintOutputData outputData) {
                fail("Success is unexpected for an empty file path.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("File path is invalid or empty.", errorMessage);
                System.out.println("Error Message: " + errorMessage);
            }

            @Override
            public void switchToMenuView() {

            }
        };

        try {
            hintInteractor.execute(emptyPathInput);
        } catch (IllegalArgumentException e) {
            assertEquals("File path cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void failureTest03_NullCountryName() {
        HintInputData nullCountryInput = new HintInputData(null, filePath);

        HintOutputBoundary failurePresenter = new HintOutputBoundary() {
            @Override
            public void prepareSuccessView(HintOutputData outputData) {
                fail("Success is unexpected for a null country name.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Country name is invalid or null.", errorMessage);
                System.out.println("Error Message: " + errorMessage);
            }

            @Override
            public void switchToMenuView() {

            }
        };

        try {
            hintInteractor.execute(nullCountryInput);
        } catch (IllegalArgumentException e) {
            assertEquals("Country name cannot be null or empty.", e.getMessage());
        }
    }
}
