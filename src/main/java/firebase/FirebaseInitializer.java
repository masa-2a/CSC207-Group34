package firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;

public class FirebaseInitializer {

    public static Firestore initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream(" add your path to the service account");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();

            FirebaseApp defaultApp = FirebaseApp.initializeApp(options);
            System.out.println(defaultApp.getName()); //"[DEFAULT]"
            System.out.println("Firebase initialized successfully.");

            Firestore db = FirestoreClient.getFirestore();
            return db;
        } catch (Exception e) {
            System.err.println("Error initializing Firebase: " + e.getMessage());
            return null;
        }
    }

}