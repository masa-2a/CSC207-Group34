package interface_adapter.round;

import interface_adapter.ViewManagerModel;
import interface_adapter.points_calculator.PointsCalculatorViewModel;
import use_case.countdown.CountdownOutputData;
import use_case.hint.HintOutputData;
import use_case.pointsCalculator.PointsCalculatorInputBoundary;
import use_case.pointsCalculator.PointsCalculatorInputData;
import use_case.round.RoundOutputBoundary;
import use_case.round.RoundOutputData;

/**
 * Presenter for the Round Use Case.
 */
public class RoundPresenter implements RoundOutputBoundary {

    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String ROUND_PRESENTER_LOG_PREFIX = "round presenter";

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
        final RoundState roundState = roundViewModel.getState();
        roundState.setViewName("Map Changed");
        roundState.setGoalLatitude(roundOutputData.getRandomLocation().get(LATITUDE));
        roundState.setGoalLongitude(roundOutputData.getRandomLocation().get(LONGITUDE));
        roundState.setCountry(roundOutputData.getCountry());

        roundViewModel.setState(roundState);
        viewManagerModel.setState(roundViewModel.getState().getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToPointsCalculator(RoundOutputData roundOutputData) {
        final RoundState roundState = roundViewModel.getState();
        roundState.setViewName("Guess received");
        roundState.setGoalLatitude(roundOutputData.getRandomLocation().get(LATITUDE));
        roundState.setGoalLongitude(roundOutputData.getRandomLocation().get(LONGITUDE));
        roundState.setGuessedLongitude(roundOutputData.getChosenLocation().get(LONGITUDE));
        roundState.setGuessedLatitude(roundOutputData.getChosenLocation().get(LATITUDE));
        roundState.setCountry(roundOutputData.getCountry());

        System.out.println(ROUND_PRESENTER_LOG_PREFIX + roundOutputData.getRandomLocation());
        System.out.println(ROUND_PRESENTER_LOG_PREFIX + roundOutputData.getChosenLocation());
        System.out.println(ROUND_PRESENTER_LOG_PREFIX + roundOutputData.getTimespent());
        System.out.println(ROUND_PRESENTER_LOG_PREFIX + roundOutputData.getHintsused());

        roundViewModel.setState(roundState);

        viewManagerModel.setState(pointsCalculatorViewModel.getViewName());
        viewManagerModel.firePropertyChanged("Points Calculator State Update");
        viewManagerModel.firePropertyChanged();
        System.out.println("triggering once");
    }

    @Override
    public void updateCountdownTimer(CountdownOutputData countdownOutputData) {
        final RoundState roundState = roundViewModel.getState();
        roundState.setTimeLeft(countdownOutputData.getTimeLeft());

        roundViewModel.setState(roundState);
        roundViewModel.setCountdownTimer();
    }

    @Override
    public void updateHints(HintOutputData hint) {
        final RoundState roundState = roundViewModel.getState();
        roundState.setHint(hint.getHint());
        roundState.setHintsUsed(hint.getHintsUsed());

        roundViewModel.setState(roundState);
        roundViewModel.setHint();
    }
}
