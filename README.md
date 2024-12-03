# CSC207-Group34 🗻🍃

**Team Name:** Map Master

Map Master is an interactive geographical guessing game that presents users with random street views from various locations around the world. The objective of the game is for the user to pinpoint the exact location of the street view on the world map as accurately as possible. 

This project was made for the course CSC207 - Software Design at University of Toronto St George Campus Fall 2024.

Created by Maira Masroor, Masa Abu Arja, Cheryl Tong, Purav Gupta and Steven Qiao.

## Table of Contents
- [Features](#features)
- [Installation Instructions](#installation-instructions)
- [Usage](#usage)
- [License](#license)
- [Feedback](#feedback)
- [Contributions](#contributions)

## Features
- **Signup and Login**: Users can create an account and log in to track their progress and compete on the leaderboard.

- **Play a New Round**: Players can start a new round where they are presented with a random street view. Players have a maximum time of 2 minutes and 30 seconds to make a guess, and they can use up to 3 hints, which will affect their points. Once they submit their guess, their earned points for this round will be displayed to them.
  
- **Leaderboard**: A leaderboard displays the highest-ranked players, sorted by their average points.

## Installation Instructions
- Clone the repository from GitHub:
  - ```bash
     git clone https://github.com/masa-2a/CSC207-Group34.git
    
- Open the project in your IDE
- **Firestore API**, which stores the user data of everyone who has played the game using your API key on any device.   
  - Head over to [Google FireBase](https://firebase.google.com/) and create a new project with Google Firestore (from the Go To Console button on the top right), you can call it Geogussr, MapMaster or whatever else you want.  
  - Then create a new collection in the Firestore data base called "Users".
  - Next, go to project settings -> Service Accounts click "Generate a new private key" for java and save that .json file somewhere safe on you device.    
  - Now go back to your IDE and check src/main/java/data_acess/firebase/FirebaseInitialiser. Notice the line that has the following code. 
  ```FileInputStream serviceAccount = new FileInputStream("/ADD/YOUR/FILE/PATH");``` add the path of the json file from step c between the qoutes instead of our filler code.  
  - Any of your friends that want to play the game must have the service key json file shared with them and they should repeat this same process. Keep in mind this key should only be shared with people you trust. You can play around with the sercuity feature of you project to delegate who can delete documents and such. 
- **Javafx SDK**
  - Download from https://gluonhq.com/products/javafx/  
  - Then unzip it and move the folder to this project's main directory.
  - Edit your run configuration and click 'Modify options'. Then Click Add VM and insert this into the space provided
  ```--module-path $PATH_TO_JAVAFX/lib --add-modules javafx.controls,javafx.fxml,javafx.web,javafx.swing --add-exports javafx.base/com.sun.javafx.logging=ALL-UNNAMED```
- **Google Maps API**       
  - Head over to [Google Cloud Console Platform](https://console.cloud.google.com/) and create a new project with Google Maps JavaScript API enabled.      
  - Then create a new API key from the hamburger menu by following API & Services -> Credentials -> Create Credentials -> API Key.
  - In the ```Map.html``` file, locate this line and replace the placeholder with your API key:
  ```<script async defer src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&callback=initialize&v=weekly"></script>```
  - Then with the API key, create a run configuration and add it as an environment variable with this  
        ```API_KEY=[Insert API Key here]```
- **Common bugs**: If you encounter the error **java.lang.RuntimeException: No toolkit found`** when running the application, follow these steps to resolve the issue:

1. Ensure JavaFX is Configured Correctly:
   - Make sure JavaFX is installed and the paths are set up properly for your system.
   - If using macOS or AArch64 architecture, ensure the correct JavaFX version is downloaded and compatible with your system.

2. Set the `--module-path` for JavaFX:
   - If using an IDE, configure the run settings:
     - **In IntelliJ IDEA**: Go to **Run > Edit Configurations**, then add the following in **VM Options**:
       ```bash
       --module-path /path/to/javafx-sdk-17.0.13/lib --add-modules javafx.controls,javafx.fxml,javafx.web,javafx.swing --add-exports javafx.base/com.sun.javafx.logging=ALL-UNNAMED
       ```
     - Replace `/path/to/javafx-sdk-17.0.13/lib` with the actual path to the `lib` directory of your JavaFX SDK.

3. Update `pom.xml` Dependencies:
   - Ensure you're using the correct, stable version of JavaFX:
     ```xml
     <dependency>
         <groupId>org.openjdk.fx</groupId>
         <artifactId>javafx-controls</artifactId>
         <version>17.0.1</version>
     </dependency>
     <dependency>
         <groupId>org.openjdk.fx</groupId>
         <artifactId>javafx-fxml</artifactId>
         <version>17.0.1</version>
     </dependency>
     ```

4. Check Your Environment Variables:
   - On non-UNIX or AArch64 systems, ensure the environment variables `JAVA_HOME` and `PATH` are set correctly for JavaFX.

5. Test JavaFX Installation:
   - Run a simple JavaFX test application to verify that JavaFX is installed correctly.

6. Update Your Java Version:
   - Ensure your Java version is compatible with the JavaFX version you're using. Check your Java version with:
     ```bash
     java -version
     ```

   
## Usage
1. In your IDE, navigate to src/main/java/app
2. Locate the Main.java file and Run it.
3. The instruction of the game are as follows.

a. Create an Account by Signing Up!

![SignUpView.png](images%2FSignUpView.png)

b. Log In

![LoginView.png](images%2FLoginView.png)

c. Now you will see the Main Menu and you can decide what you would like to do by clicking the buttons.

![MainMenuView.png](images%2FMainMenuView.png)

- When you click "new round" you will be met with a google street view map and a world view map.  
    1. You must guess the location of the street view map and place a guess on the world view map.
       ![image](https://github.com/user-attachments/assets/0a79c604-4612-4f36-9790-2a43ee2bc020)
    2. If you would like you can check the hints, press the button and you have three possible hints with varying difficulty. the hints are categorized into 1. year of establishment 2. official languages 3. flags of the country where the random location is at.
       ![image](https://github.com/user-attachments/assets/91c93a13-4176-41c8-a1c2-65ed6db744ac)
    3. After you submit your coordinates on the pop up browser, press the guess submit on the original and you will be given points depending on how accurate your guess is, how long you took and how many hints you used.
       ![image](https://github.com/user-attachments/assets/da8f9cd5-f963-44fb-b3ee-161d680696a2)
       
- When you click "Leaderboard" you will see the top 3 players of MapMaster and your ranking in comparison!
  ![image](https://github.com/user-attachments/assets/614f8278-bb84-44b3-a04f-d6596e85477a)
- When you click "Logout", you are taken to the logout screen!
   

## License

CC0 1.0 Universal (CC0)

The copyright holder(s) have waived all their rights to this work, including all related and neighboring rights, to the extent possible under law. This work is now in the public domain, and you may copy, modify, distribute, or perform the work, even for commercial purposes, without asking permission.

The work is provided "as is", without any warranties of any kind, either express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose, or noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages, or other liability arising from, out of, or in connection with the work or the use or other dealings in the work.



## Feedback
We welcome any feedback you have! Please fill out the form below to share any thoughts on the game, including:
  - Bugs you encounter
  - Suggestions for new features
  - General thoughts on usability and design
  - Anything else you think could help improve Map Master!

 Please be as detailed as possible to help us improve the game. [Feedback Form](https://forms.gle/okprPAfZ1eHAPpi89)

## Contributions
We welcome contributions from the community! To contribute to this project, follow these steps:

1. **Fork the repository** to your GitHub account.
2. **Clone the fork** to your local machine.
3. **Create a new branch** for your changes:
   ```bash
   git checkout -b your-branch-name
   ```
4. **Make your changes** (e.g., bug fixes, feature additions).
5. **Commit your changes** with a clear message:
   ```bash
   git commit -m "Description of changes"
   ```
6. **Push your changes**:
   ```bash
   git push origin your-branch-name
   ```
7. **Create a pull request**:
   - Provide a detailed description of the changes and why they are important.
   - If your PR addresses a specific issue, link to that issue (e.g., "Fixes #45").
8. **Code review**:
   - Once the pull request is submitted, the maintainers will review your code and provide feedback.
   - Be open to feedback and make necessary updates.












