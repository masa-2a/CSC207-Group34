package data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import entity.CommonUser;
import entity.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.leaderboard.LeaderboardUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.pointsCalculator.PointsCalculatorDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * DAO for Firestore.
 */
public class FirestoreDataAccessObject
        extends AbstractDataAccessObject
        implements LoginUserDataAccessInterface,
        SignupUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        LeaderboardUserDataAccessInterface,
        PointsCalculatorDataAccessInterface {

    private static final String NAME = "name";
    private static final String NUMBEROFGAMES = "numberOfGames";
    private static final String PASSWORD = "password";
    private static final String POINTS = "points";
    private static final String USERSCOLLECTION = "Users";
    private static final int LIMIT = 3;
    private final Firestore firestore;
    private String currentUsername;
    private User currentUser;

    /**
     * Initialises firestore DAO.
     */
    public FirestoreDataAccessObject() {
        this.firestore = FirebaseInitializer.initializeFirebase();
        this.currentUsername = "";
    }

    @Override
    public void save(User user) {
        try {
            // creates new document in User collection with id being username
            final DocumentReference userRef = firestore.collection(USERSCOLLECTION).document(user.getName());
            // create map with user data
            final Map<String, Object> userData = new HashMap<>();
            userData.put(NAME, user.getName());
            userData.put(PASSWORD, user.getPassword());
            userData.put(POINTS, user.getPoints());
            userData.put(NUMBEROFGAMES, user.getNumberOfGames());

            userRef.set(userData);
            System.out.println(user.getName() + " saved to Firestore.");
        }
        catch (Exception ex) {
            System.err.println("Error saving user data to Firestore: " + ex.getMessage());
        }
    }

    @Override
    public User get(String username) {
        try {
            final DocumentReference userRef = firestore.collection(USERSCOLLECTION).document(username);
            final DocumentSnapshot documentSnapshot = userRef.get().get();
            if (documentSnapshot.exists()) {
                final Map<String, Object> userData = documentSnapshot.getData();
                final String name = (String) userData.get(NAME);
                final String password = (String) userData.get(PASSWORD);
                final int points = ((Long) userData.get(POINTS)).intValue();
                final int gamesPlayed = ((Long) userData.get(NUMBEROFGAMES)).intValue();

                return new CommonUser(name, password, points, gamesPlayed);
            }
            else {
                System.out.println("User does not exist.");
                return null;
            }
        }
        catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error retrieving user from Firestore: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    @Override
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public boolean existsByName(String username) {
        try {
            final DocumentReference userRef = firestore.collection(USERSCOLLECTION).document(username);
            final DocumentSnapshot documentSnapshot = userRef.get().get();
            return documentSnapshot.exists();
        }
        catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error checking user existence in Firestore: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public void changePassword(User user) {
        try {
            final DocumentReference userRef = firestore.collection(USERSCOLLECTION).document(user.getName());
            userRef.update(PASSWORD, user.getPassword());
            setCurrentUser(user);
            System.out.println("Password updated for user: " + user.getName());
        }
        catch (Exception ex) {
            System.err.println("Error changing password in Firestore: " + ex.getMessage());
        }
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void addEarnedPoints(int pointsEarned, User user) {
        try {
            final DocumentReference userRef = firestore.collection(USERSCOLLECTION).document(user.getName());
            userRef.update(POINTS, FieldValue.increment(pointsEarned));
            System.out.println("Earned points added for user: " + user.getName());
        }
        catch (Exception ex) {
            System.err.println("Error adding earned points to Firestore: " + ex.getMessage());
        }

    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }

    /**
     * Returns a list of all the users in the data base.
     *
     * @return ArrayList allUsers
     */
    @Override
    public ArrayList<CommonUser> returnAllUsers() {
        final ArrayList<CommonUser> allUsers = new ArrayList<>();
        final ApiFuture<QuerySnapshot> query = firestore.collection(USERSCOLLECTION).get();

        try {
            final QuerySnapshot querySnapshot = query.get();
            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                // Extract fields from the document
                final String name = document.getString(NAME);
                final String password = document.getString(PASSWORD);
                final Long points = document.getLong(POINTS);
                final Long numberOfGames = document.getLong(NUMBEROFGAMES);

                // Ensure fields are not null and convert to CommonUser
                if (name != null && password != null && points != null && numberOfGames != null) {
                    final CommonUser user = new CommonUser(name, password, points.intValue(), numberOfGames.intValue());
                    allUsers.add(user);
                }
            }
        }
        catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error retrieving top users: " + ex.getMessage());
        }
        return allUsers;
    }

    /**
     * Retrieves the top 3 users based on points.
     *
     * @return a list of the top 3 CommonUser objects.
     */
    public ArrayList<CommonUser> topUsers() {
        final ArrayList<CommonUser> topUsersList = new ArrayList<>();

        // Query Firestore to fetch top 3 users ordered by points in descending order
        final ApiFuture<QuerySnapshot> query = firestore.collection(USERSCOLLECTION)
                .orderBy(POINTS, Query.Direction.DESCENDING)
                .limit(LIMIT)
                .get();

        try {
            final QuerySnapshot querySnapshot = query.get();
            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                // Extract fields from the document
                final String name = document.getString(NAME);
                final String password = document.getString(PASSWORD);
                final Long points = document.getLong(POINTS);
                final Long numberOfGames = document.getLong(NUMBEROFGAMES);

                // Ensure fields are not null and convert to CommonUser
                if (name != null && password != null && points != null && numberOfGames != null) {
                    final CommonUser user = new CommonUser(name, password, points.intValue(), numberOfGames.intValue());
                    topUsersList.add(user);
                }
            }
        }
        catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error retrieving top users: " + ex.getMessage());
        }

        return topUsersList;
    }
}
