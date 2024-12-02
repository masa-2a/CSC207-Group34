# CSC207-Group34 🗻🍃

**Team Name:** Map Master

Map Master is an interactive geographical guessing game that presents users with random street views from various locations around the world. The objective of the game is for the user to pinpoint the exact location of the street view on the world map as accurately as possible. 

This project was made for the course CSC207 - Software Design at University of Toronto St George Campus Fall 2024.

## Table of Contents
- [Features](#features)
- [Installation Instructions](#installation-instructions)
- [Usage](#usage)
- [License](#license)
- [Feedback and Contributions](#feedback-and-contributions)

## Features
- **Signup and Login**: Users can create an account and log in to track their progress and compete on the leaderboard.

- **Play a New Round**: Players can start a new round where they are presented with a random street view. Players have a maximum time of 2 minutes and 30 seconds to make a guess, and they can use up to 3 hints, which will affect their points. Once they submit their guess, their earned points for this round will be displayed to them.
- **Leaderboard**: A leaderboard displays the highest-ranked players, sorted by their average points.

## Installation Instructions
- Clone the repository from GitHub:
  - ```bash
     git clone https://github.com/masa-2a/CSC207-Group34.git
    
- Open the project in your IDE
- Firestore API, which stores the user data of everyone who has played the game using your API key on any device.   
  - Head over to [Google FireBase](https://firebase.google.com/) and create a new project with Google Firestore (from the Go To Console button on the top right), you can call it Geogussr, MapMaster or whatever else you want.  
  - Then create a new collection in the Firestore data base called "Users".
  - Then create a new collection in the Firestore data base called "Users".
  - Next, go to project settings -> Service Accounts click "Generate a new private key" for java and save that .json file somewhere safe on you device.    
  - Now go back to your IDE and check src/main/java/data_acess/firebase/FirebaseInitialiser. Notice the line that has the following code. 
  ```FileInputStream serviceAccount = new FileInputStream("/ADD/YOUR/FILE/PATH");``` add the path of the json file from step c between the qoutes instead of our filler code.  
  - Any of your friends that want to play the game must have the service key json file shared with them and they should repeat this same process. Keep in mind this key should only be shared with people you trust. You can play around with the sercuity feature of you project to delegate who can delete documents and such. 
- Javafx SDK
  - Download from https://gluonhq.com/products/javafx/  
  - Then unzip it and move the folder to this project's main directory.
  - Edit your run configuration and click 'Modify options'. Then Click Add VM and insert this into the space provided
  ```--module-path $PATH_TO_JAVAFX/lib --add-modules javafx.controls,javafx.fxml,javafx.web,javafx.swing --add-exports javafx.base/com.sun.javafx.logging=ALL-UNNAMED```
- Google Maps API         
  - Head over to [Google Cloud Console Platform](https://console.cloud.google.com/) and create a new project with Google Maps JavaScript API enabled.      
  - Then create a new API key from the hamburger menu by following API & Services -> Credentials -> Create Credentials -> API Key.
  - In the ```Map.html``` file, locate this line and replace the placeholder with your API key:
  ```<script async defer src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&callback=initialize&v=weekly"></script>```
  - Then with the API key, create a run configuration and add it as an environment variable with this  
        ```API_KEY=[Insert API Key here]```
   
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
    2. If you would like you can check the hints, you have three possible hints with varying difficulty.  
    3. After you submit your guess you will be given points depending on how accurate your guess is, how long you took and how many hints you used.  
- When you click "Leaderboard" you will see the top 3 players of MapMaster and your ranking in comparison!
- When you click "Logout", you are taken to the logout screen!
   

## License
MIT License

Copyright (c) 2024 Map Master Team

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


## Feedback and contributions
Created by Maira Masroor, Masa Abu Arja, Cheryl Tong, Purav Gupta and Steven Qiao.
Please fill out this form if you have any feedback: https://forms.gle/okprPAfZ1eHAPpi89












