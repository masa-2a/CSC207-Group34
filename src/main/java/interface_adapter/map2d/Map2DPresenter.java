package interface_adapter.map2d;

import interface_adapter.ViewManagerModel;
import use_case.map2d.Map2DOutputBoundary;
import use_case.map2d.Map2DOutputData;

/**
 * The presenter for the Map2D Use Case.
 */
public class Map2DPresenter implements Map2DOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final Map2DViewModel map2DViewModel;

    public Map2DPresenter(ViewManagerModel viewManagerModel, Map2DViewModel map2DViewModel) {
        this.map2DViewModel = map2DViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareMapView(Map2DOutputData outputData) {
        // On success, switch to the 2D map view.
        final Map2DState map2DState = map2DViewModel.getState();

        this.map2DViewModel.setState(map2DState);
        this.map2DViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the 2D map view.
        this.viewManagerModel.setState(map2DViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
