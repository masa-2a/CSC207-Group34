package interface_adapter.map2d;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Map2D Use Case.
 */
public class Map2DViewModel extends ViewModel<Map2DState> {

    public Map2DViewModel() {
        super("New 2D map created");
        setState(new Map2DState());
    }
}
