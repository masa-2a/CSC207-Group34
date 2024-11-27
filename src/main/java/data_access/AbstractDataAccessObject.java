package data_access;

import entity.User;

public abstract class AbstractDataAccessObject {
    // Save a user object to Firestore
    public void save(User user){}

    // Retrieve a user object by username from Firestore
    public User get(String username){ return null;}

    // Set the current logged-in user
    public void setCurrentUsername(String name){}

    // Check if a user exists in Firestore by their username
    public boolean existsByName(String username){return false;}

    // Change the password for a user
    public void changePassword(User user){}

    // Get the current logged-in username
    public String getCurrentUsername(){return null;}

    public void setCurrentUser() {};

}
