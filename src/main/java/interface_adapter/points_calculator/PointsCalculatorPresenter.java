package interface_adapter.points_calculator;

import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputData;
import use_case.points_calculator.PointsCalculatorInputBoundary;
import use_case.points_calculator.PointsCalculatorOutputBoundary;
import use_case.points_calculator.PointsCalculatorOutputData;

public class PointsCalculatorPresenter implements PointsCalculatorOutputBoundary {
    private final PointsCalculatorViewModel pointsCalculatorViewModel;
    private final ViewManagerModel viewManagerModel;

    public PointsCalculatorPresenter(PointsCalculatorViewModel pointsCalculatorViewModel, ViewManagerModel viewManagerModel) {
        this.pointsCalculatorViewModel = pointsCalculatorViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view for the PointsCalculator Use Case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(PointsCalculatorOutputData outputData) {




    }
}
