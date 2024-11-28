package use_case.points_calculator;

import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class PointsCalculatorInteractorTest {

    private PointsCalculatorDataAccessInterface pointsDataAccessObject;

    // Inner class implementation of PointsCalculatorDataAccessInterface
    private static class InMemoryPointsCalculatorDataAccessObject implements PointsCalculatorDataAccessInterface {

        private Map<String, User> userStore = new HashMap<>();
        private String currentUsername = "Paul"; // Hardcoded username for testing

        @Override
        public String getCurrentUsername() {
            return currentUsername;
        }

        @Override
        public User get(String username) {
            return userStore.get(username);
        }

        // Save a user into the in-memory store
        public void save(User user) {
            userStore.put(user.getName(), user);
        }
    }

    @BeforeEach
    public void setUp() {
        // Initialize the in-memory data access object before each test
        pointsDataAccessObject = new InMemoryPointsCalculatorDataAccessObject();

        // Add a test user to the in-memory data access object
        User user = new CommonUser("Paul", "password");  // User "Paul" with 0 points
        ((InMemoryPointsCalculatorDataAccessObject) pointsDataAccessObject).save(user);
    }


    @Test
    void successTest() {

        Map<String, Double> randomLocation = new HashMap<>();
        randomLocation.put("latitude", 43.7);
        randomLocation.put("longitude", -79.42);

        Map<String, Double> chosenLocation = new HashMap<>();
        chosenLocation.put("latitude", 49.28);
        chosenLocation.put("longitude", -123.12);

        PointsCalculatorInputData inputData = new PointsCalculatorInputData(randomLocation, chosenLocation, 80, 1, "C:/Users/maira/IdeaProjects/CSC207-Group34/images/GUI plan.png");

        PointsCalculatorOutputBoundary successPresenter = new PointsCalculatorOutputBoundary() {
            @Override
            public void prepareSuccessView(PointsCalculatorOutputData outputData) {
                assertEquals(4584, outputData.getPoints());
                assertEquals("C:/Users/maira/IdeaProjects/CSC207-Group34/images/GUI plan.png", outputData.getImagepath());
                assertEquals("You scored 4584 points!", outputData.getMessage());

            }

            /**
             * Switches to the Main Menu View.
             */
            @Override
            public void switchToMenuView() {

            }

        };
        PointsCalculatorInputBoundary interactor = new PointsCalculatorInteractor(pointsDataAccessObject, successPresenter);
        interactor.execute(inputData);
        interactor.switchToMenuView();

    }

    @Test
    void failureTest() {
        Map<String, Double> randomLocation = new HashMap<>();

        Map<String, Double> chosenLocation = new HashMap<>();

        PointsCalculatorInputData inputData = new PointsCalculatorInputData(randomLocation, chosenLocation, 80, 1, "C:/Users/maira/IdeaProjects/CSC207-Group34/images/GUI plan.png");


        PointsCalculatorOutputBoundary failurePresenter = new PointsCalculatorOutputBoundary() {
            /**
             * Prepares the success view for the PointsCalculator Use Case.
             *
             * @param outputData the output data
             */
            @Override
            public void prepareSuccessView(PointsCalculatorOutputData outputData) {
                fail("Use case success is unexpected.");

            }

            /**
             * Switches to the Main Menu View.
             */
            @Override
            public void switchToMenuView() {

            }
        };
        PointsCalculatorInputBoundary interactor = new PointsCalculatorInteractor(pointsDataAccessObject, failurePresenter);
        try {
            interactor.execute(inputData);  // This should throw an IllegalArgumentException
            fail("Expected IllegalArgumentException to be thrown.");  // This line ensures that if no exception is thrown, the test fails
        } catch (IllegalArgumentException e) {
            // Check that the exception message is as expected
            assertEquals("Random location and Chosen location must contain 'latitude' and 'longitude' keys.", e.getMessage());
        }

        }
}





