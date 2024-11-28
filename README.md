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
1. Clone the repository from GitHub:
   ```bash
   git clone https://github.com/masa-2a/CSC207-Group34.git
2. Open the project in your IDE
3. Firestore API, which stores the user data of everyone who has played the game using your API key on any device.   
         a. Head over to [Google FireBase](https://firebase.google.com/) and create a new project with Google Firestore (from the Go To Console button on the top                 right), you can call it Geogussr, MapMaster or whatever else you want.  
         b. Then create a new collection in the Firestore data base called "Users".
         c. Next, go to project settings -> Service Accounts click "Generate a new private key" for java and save that .json file somewhere safe on you device.    
         c. Now go back to your IDE and check src/main/java/firebase/FirebaseInitialiser. Notice the line that has the following code. 
      ```FileInputStream serviceAccount = new FileInputStream("/ADD/YOUR/FILE/PATH");```  
            add the path of the json file from step c between the qoutes instead of our filler code.  
         e. Any of your friends that want to play the game must have the service key json file shared with them and they should repeat this same process. Keep in                 mind this key should only be shared with people you trust. You cna play around with the sercuity feature of you project to delegate who can delete                    documents and such.
4. Javafx SDK   
         a. Download from https://gluonhq.com/products/javafx/  
         b. Then unzip it and move the folder to this project's main directory.

5. Google API, //add pls



## Usage
1. In your IDE, navigate to src/main/java/app
2. Locate the Main.java file and Run it.
3. The instruction of the game are as follows.
    a. When you clicl "new round" you will be met with a google street view map and a world view map.  
    b. You must guess the location of the street view map and place a guess on the world view map.  
    c. If you would like you can check the hints, you have three possible hints with varying difficulty.  
    d. After you submit your guess you will be given points depending on how accurate your guess is, how long you took and how many hints you used.  
   After this you can choose weither to play another game, logout or check the leaderboard. 
   

## License
MIT License

Copyright (c) 2024 Map Master Team

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


## Feedback and contributions
Created by Maira Masroor, Masa Abu Arja, Cheryl Tong, Purav Gupta and Steven Qiao.
Please fill out this form: https://forms.gle/okprPAfZ1eHAPpi89












