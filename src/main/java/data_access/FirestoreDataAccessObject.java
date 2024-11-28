package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import entity.CommonUser;
import entity.User;
import firebase.FirebaseInitializer;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.leaderboard.LeaderboardUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.pointsCalculator.PointsCalculatorDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class FirestoreDataAccessObject extends AbstractDataAccessObject implements LoginUserDataAccessInterface, SignupUserDataAccessInterface, ChangePasswordUserDataAccessInterface, LogoutUserDataAccessInterface, LeaderboardUserDataAccessInterface, PointsCalculatorDataAccessInterface {

    private final Firestore firestore;
    private String currentUsername;
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String POINTS = "points";
    private static final String NUMBEROFGAMES = "numberOfGames";
    private static final String USERSCOLLECTION = "Users";

    // Constructor to initialize Firestore instance
    public FirestoreDataAccessObject() {
        this.firestore = FirebaseInitializer.initializeFirebase();
        this.currentUsername = "";  // Default state, no user logged in
    }

    @Override
    public void save(User user) {
        try {
            //creates new document in User collection with id being username
            DocumentReference userRef = firestore.collection(USERSCOLLECTION).document(user.getName());
            // create map with user data
            Map<String, Object> userData = new HashMap<>();
            userData.put(NAME, user.getName());
            userData.put(PASSWORD, user.getPassword());
            userData.put(POINTS, user.getPoints());
            userData.put(NUMBEROFGAMES, user.getNumberOfGames());

            ApiFuture<WriteResult> result = userRef.set(userData);
            System.out.println(user.getName()+" saved to Firestore.");
        } catch (Exception e) {
            System.err.println("Error saving user data to Firestore: " + e.getMessage());
        }
    }

    @Override
    public User get(String username) {
        try {
            DocumentReference userRef = firestore.collection(USERSCOLLECTION).document(username);
            DocumentSnapshot documentSnapshot = userRef.get().get();  // Blocking call to get the document
            if (documentSnapshot.exists()) {
                Map<String, Object> userData = documentSnapshot.getData();
                String name = (String) userData.get(NAME);
                String password = (String) userData.get(PASSWORD);
                int points = ((Long) userData.get(POINTS)).intValue();  // Firestore stores numbers as Long
                int gamesPlayed = ((Long) userData.get(NUMBEROFGAMES)).intValue(); // Similarly for gamesPlayed

                return new CommonUser(name, password, points, gamesPlayed);
            } else {
                System.out.println("User does not exist.");
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error retrieving user from Firestore: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    @Override
    public boolean existsByName(String username) {
        try {
            DocumentReference userRef = firestore.collection(USERSCOLLECTION).document(username);
            DocumentSnapshot documentSnapshot = userRef.get().get();
            return documentSnapshot.exists();  // Return true if user exists
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error checking user existence in Firestore: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void changePassword(User user) {
        try {
            DocumentReference userRef = firestore.collection(USERSCOLLECTION).document(user.getName());
            userRef.update(PASSWORD, user.getPassword());
            System.out.println("Password updated for user: " + user.getName());
        } catch (Exception e) {
            System.err.println("Error changing password in Firestore: " + e.getMessage());
        }
    }

    @Override
    public void addEarnedPoints(int pointsEarned, User user) {
        try {
            DocumentReference userRef = firestore.collection(USERSCOLLECTION).document(user.getName());
            userRef.update(POINTS, FieldValue.increment(pointsEarned));
            System.out.println("Earned points added for user: " + user.getName());
        }
        catch (Exception e) {
            System.err.println("Error adding earned points to Firestore: " + e.getMessage());
        }

    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }


    @Override
    public List<CommonUser> returnAllUsers() {
    return null;
    }
}
