package interface_adapter.points_calculator;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MenuViewModel;
import use_case.pointsCalculator.PointsCalculatorOutputBoundary;
import use_case.pointsCalculator.PointsCalculatorOutputData;

/**
 * Presenter for the PointsCalculator Use Case.
 */
public class PointsCalculatorPresenter implements PointsCalculatorOutputBoundary {
    private final PointsCalculatorViewModel pointsCalculatorViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuViewModel menuViewModel;

    /**
     * Presenter for Points Calculator.
     *
     * @param pointsCalculatorViewModel the Points Calculator ViewModel.
     * @param viewManagerModel the View Manager Model.
     * @param menuViewModel the Menu ViewModel.
     */
    public PointsCalculatorPresenter(PointsCalculatorViewModel pointsCalculatorViewModel,
                                     ViewManagerModel viewManagerModel,
                                     MenuViewModel menuViewModel) {
        this.pointsCalculatorViewModel = pointsCalculatorViewModel;
        this.viewManagerModel = viewManagerModel;
        this.menuViewModel = menuViewModel;
    }

    /**
     * Prepares the success view for the PointsCalculator Use Case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(PointsCalculatorOutputData outputData) {

        //        pointsCalculatorViewModel.updatePoints(outputData.getPointsEarned());
        //        pointsCalculatorViewModel.updateMessage(outputData.getMessage());
        //        pointsCalculatorViewModel.updateImagePath(outputData.getImagePath());
        System.out.println("prepare success view is run");
        final PointsCalculatorState pointsCalculatorState = pointsCalculatorViewModel.getState();
        pointsCalculatorState.updatePoints(outputData.getPointsEarned());
        pointsCalculatorState.setMessage(outputData.getMessage());
        pointsCalculatorViewModel.setState(pointsCalculatorState);
        pointsCalculatorViewModel.firePropertyChanged("Points Calculator State Update");
    }

    /**
     * Switches to the Main Menu View.
     */
    @Override
    public void switchToMenuView() {
        viewManagerModel.setState(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
