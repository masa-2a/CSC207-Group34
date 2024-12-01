package data_access.firebase;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

/**
 * A class that initializes Firebase.
 */
public class FirebaseInitializer {

    /**
     * Initializes Firebase.
     *
     * @return the Firestore instance
     */
    public static Firestore initializeFirebase() {
        try {
            final FileInputStream serviceAccount =
                    new FileInputStream("/Users/masaarja/Desktop/geoguesser_firebase_service_account.json");
            final GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            final FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();

            final FirebaseApp defaultApp = FirebaseApp.initializeApp(options);
            System.out.println(defaultApp.getName());
            System.out.println("Firebase initialized successfully.");

            final Firestore db = FirestoreClient.getFirestore();
            return db;
        }
        catch (IOException ioException) {
            System.err.println("Error initializing Firebase: " + ioException.getMessage());
            return null;
        }

    }
}
