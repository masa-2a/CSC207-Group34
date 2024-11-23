package interface_adapter.map2d;

import use_case.map2d.Map2DOutputBoundary;
import use_case.map2d.Map2DOutputData;

public class Map2DPresenter implements Map2DOutputBoundary {
    private final Map2DViewModel map2DViewModel;

    public Map2DPresenter(Map2DViewModel map2DViewModel){
        this.map2DViewModel = map2DViewModel;
    }

    @Override
    public void prepareSuccessView(Map2DOutputData outputData) {
        // On success, switch to the 2D map view.
        final Map2DState map2DState = map2DViewModel.getState();

        this.map2DViewModel.setState(map2DState);
        this.map2DViewModel.firePropertyChanged();
    }
}
