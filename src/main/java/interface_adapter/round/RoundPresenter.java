package interface_adapter.round;

import interface_adapter.ViewManagerModel;
import interface_adapter.points_calculator.PointsCalculatorViewModel;

import use_case.pointsCalculator.PointsCalculatorInputBoundary;
import use_case.pointsCalculator.PointsCalculatorInputData;
import use_case.countdown.CountdownOutputData;
import use_case.round.RoundOutputBoundary;
import use_case.round.RoundOutputData;

public class RoundPresenter implements RoundOutputBoundary {

    private final RoundViewModel roundViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PointsCalculatorViewModel pointsCalculatorViewModel;
    private final PointsCalculatorInputBoundary pointsCalculatorInteractor;

    public RoundPresenter(RoundViewModel roundViewModel,
                          ViewManagerModel viewManagerModel,
                          PointsCalculatorViewModel pointsCalculatorViewModel,PointsCalculatorInputBoundary pointsCalculatorInteractor) {
        this.roundViewModel = roundViewModel;
        this.viewManagerModel = viewManagerModel;
        this.pointsCalculatorViewModel = pointsCalculatorViewModel;
        this.pointsCalculatorInteractor = pointsCalculatorInteractor;
    }

    /**
     * Switches to the Map View.
     */
    @Override
    public void presentMapData(RoundOutputData roundOutputData) {
        RoundState roundState = roundViewModel.getState();
        roundState.setViewName("Map Changed");
        roundState.setGoalLatitude(roundOutputData.getRandomLocation().get("latitude"));
        roundState.setGoalLongitude(roundOutputData.getRandomLocation().get("longitude"));

        roundViewModel.setState(roundState);
        viewManagerModel.setState(roundViewModel.getState().getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToPointsCalculator(RoundOutputData roundOutputData) {
        RoundState roundState = roundViewModel.getState();
        roundState.setViewName("Guess received");
        roundState.setGoalLatitude(roundOutputData.getRandomLocation().get("latitude"));
        roundState.setGoalLongitude(roundOutputData.getRandomLocation().get("longitude"));
        roundState.setGuessedLongitude(roundOutputData.getChosenLocation().get("longitude"));
        roundState.setGuessedLatitude(roundOutputData.getChosenLocation().get("latitude"));

        System.out.println(roundOutputData.getRandomLocation());
        System.out.println(roundOutputData.getChosenLocation());


        PointsCalculatorInputData inputData = new PointsCalculatorInputData(
                roundOutputData.getRandomLocation(), roundOutputData.getChosenLocation(),
                roundOutputData.getTimespent(), roundOutputData.getHintsused(),
                roundOutputData.getImagepath());
        pointsCalculatorInteractor.execute(inputData);

//        roundViewModel.setState(roundState);
//
//        pointsCalculatorViewModel.getState().updateChosenLocation(roundOutputData.getRandomLocation());
//        pointsCalculatorViewModel.getState().updateTimespent(roundOutputData.getTimespent());
//        pointsCalculatorViewModel.getState().updateHintsused(roundOutputData.getHintsused());
//        pointsCalculatorViewModel.getState().updateImagePath(roundOutputData.getImagepath());
//        pointsCalculatorViewModel.getState().updateRandomLocation(roundOutputData.getRandomLocation());

        viewManagerModel.setState(pointsCalculatorViewModel.getViewName());
        viewManagerModel.firePropertyChanged("Points Calculator State Update");
        viewManagerModel.firePropertyChanged();
        System.out.println("triggering once");
    }

    @Override
    public void updateCountdownTimer(CountdownOutputData countdownOutputData) {
        RoundState roundState = roundViewModel.getState();
        roundState.setTimeLeft(countdownOutputData.getTimeLeft());

        roundViewModel.setState(roundState);
        roundViewModel.setCountdownTimer(roundState.getTimeLeft());
    }
}
