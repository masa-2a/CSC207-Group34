package interface_adapter.round;

import interface_adapter.ViewManagerModel;
import interface_adapter.points_calculator.PointsCalculatorViewModel;
import use_case.round.RoundOutputBoundary;
import use_case.round.RoundOutputData;

public class RoundPresenter implements RoundOutputBoundary {

    private final RoundViewModel roundViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PointsCalculatorViewModel pointsCalculatorViewModel;

    public RoundPresenter(RoundViewModel roundViewModel,
                          ViewManagerModel viewManagerModel,
                          PointsCalculatorViewModel pointsCalculatorViewModel) {
        this.roundViewModel = roundViewModel;
        this.viewManagerModel = viewManagerModel;
        this.pointsCalculatorViewModel = pointsCalculatorViewModel;
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

        roundViewModel.setState(roundState);
        viewManagerModel.setState(pointsCalculatorViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
