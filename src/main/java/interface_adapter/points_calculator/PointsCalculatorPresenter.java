package interface_adapter.points_calculator;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MenuViewModel;
import use_case.pointsCalculator.PointsCalculatorOutputBoundary;
import use_case.pointsCalculator.PointsCalculatorOutputData;

public class PointsCalculatorPresenter implements PointsCalculatorOutputBoundary {
    private final PointsCalculatorViewModel pointsCalculatorViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuViewModel menuViewModel;

    /**
     * Presenter for Points Calculator
     *
     * @param pointsCalculatorViewModel
     * @param viewManagerModel
     * @param menuViewModel
     */
    public PointsCalculatorPresenter(PointsCalculatorViewModel pointsCalculatorViewModel, ViewManagerModel viewManagerModel, MenuViewModel menuViewModel) {
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
        pointsCalculatorViewModel.updatePoints(outputData.getPointsEarned());
//        pointsCalculatorViewModel.updateMessage(outputData.getMessage());
        pointsCalculatorViewModel.updateImagePath(outputData.getImagePath());
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
