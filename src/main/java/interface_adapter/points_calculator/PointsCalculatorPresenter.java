package interface_adapter.points_calculator;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.main_menu.MenuViewModel;
import use_case.login.LoginOutputData;
import use_case.points_calculator.PointsCalculatorInputBoundary;
import use_case.points_calculator.PointsCalculatorOutputBoundary;
import use_case.points_calculator.PointsCalculatorOutputData;

public class PointsCalculatorPresenter implements PointsCalculatorOutputBoundary {
    private final PointsCalculatorViewModel pointsCalculatorViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuViewModel menuViewModel;

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
    pointsCalculatorViewModel.updatePoints(outputData.getPoints());
    pointsCalculatorViewModel.updateMessage(outputData.getMessage());
    pointsCalculatorViewModel.updateImagePath(outputData.getImagepath());


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
