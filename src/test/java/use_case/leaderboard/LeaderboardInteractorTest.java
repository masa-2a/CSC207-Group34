package use_case.leaderboard;

import data_access.non_persisting.InMemoryUserDataAccessObject;
import CommonUser;
import entity.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardInteractorTest {
    InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
    User paul = new CommonUser("Paul", "2020", 300, 6);
    User mushu = new CommonUser("Mushu", "meow", 247, 3);
    User maira = new CommonUser("Maira", "masroor", 768, 2); // first
    User taleen = new CommonUser("Taleen", "slayed", 456, 5); //third
    User ash = new CommonUser("Ash", "ash", 550, 3); // second

    @BeforeEach
    void setUp() {
        //populate database
        userDataAccessObject.save(paul);
        userDataAccessObject.save(mushu);
        userDataAccessObject.save(maira);
        userDataAccessObject.save(taleen);
        userDataAccessObject.save(ash);


    }

    @Test
    void successTestUser1() {
        //mushu user logged in
        userDataAccessObject.setCurrentUser(maira);
        LeaderboardUserDataAccessInterface dataAccessInterface = new InMemoryUserDataAccessObject();

        LeaderboardOutputBoundary successPresenter = new LeaderboardOutputBoundary() {
            @Override
            public void prepareSuccessView(LeaderboardOutputData outputData) {

                assertEquals(maira.getName(), outputData.getTopUsers().get(1).getName());
                assertEquals(ash.getName(), outputData.getTopUsers().get(2).getName());
                assertEquals(taleen.getName(), outputData.getTopUsers().get(3).getName());
                assertEquals(maira.getName(), outputData.getCurrentUsername());
                assertEquals(1, outputData.getCurrentUserRank());

            }

            @Override
            public void prepareFailView(String errorMessage) {
                //leaderboard cannot have a failed view
            }

            @Override
            public void switchToMenuView() {
                //this cannot be tested
            }

        };
        LeaderboardInputData leaderboardInputData = new LeaderboardInputData(userDataAccessObject.getCurrentUsername());
        LeaderboardInputBoundary leaderboardInteractor = new LeaderboardInteractor(successPresenter, userDataAccessObject);
        leaderboardInteractor.execute(leaderboardInputData);
        leaderboardInteractor.switchToMenuView();
    }

}