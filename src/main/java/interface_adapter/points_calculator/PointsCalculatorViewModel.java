package interface_adapter.points_calculator;

import interface_adapter.ViewModel;

public class PointsCalculatorViewModel extends ViewModel<PointsCalculatorState> {
    public static final String TITLE_LABEL = "Points Display";
    public static final String TO_MENU_BUTTON_LABEL = "To Menu";

    public PointsCalculatorViewModel() {
        super("PointsCalculatorView");
        setState(new PointsCalculatorState());

    }

    /**
     * Updates the points in the state and notifies observers of the change.
     * @param points the new points value to set
     */
    public void updatePoints(int points){
        this.getState().updatePoints(points);
        firePropertyChanged();

    }

//    public void updateMessage(String message){
//        this.getState().updateMessage(message);
//        firePropertyChanged();
//    }

    public void updateImagePath(String imagepath){
        this.getState().updateImagePath(imagepath);
        firePropertyChanged();
    }

}
