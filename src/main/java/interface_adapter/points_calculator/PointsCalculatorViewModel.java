package interface_adapter.points_calculator;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Points Calculator Use Case.
 */
public class PointsCalculatorViewModel extends ViewModel<PointsCalculatorState> {
    public static final String TITLE_LABEL = "Points Display";
    public static final String TO_MENU_BUTTON_LABEL = "To Menu";

    public PointsCalculatorViewModel() {
        super("PointsCalculatorView");
        setState(new PointsCalculatorState());

    }

}
