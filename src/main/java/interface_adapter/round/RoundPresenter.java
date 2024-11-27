package interface_adapter.round;

import interface_adapter.ViewManagerModel;
import interface_adapter.map2d.Map2DViewModel;
import use_case.round.RoundOutputBoundary;
import interface_adapter.round.RoundViewModel;

public class RoundPresenter implements RoundOutputBoundary {

    private final RoundViewModel roundViewModel;
    private final Map2DViewModel map2DViewModel;
    private final ViewManagerModel viewManagerModel;

    public RoundPresenter(RoundViewModel roundViewModel, Map2DViewModel map2DViewModel,
                          ViewManagerModel viewManagerModel) {
        this.roundViewModel = roundViewModel;
        this.map2DViewModel = map2DViewModel;
        this.viewManagerModel = viewManagerModel;
    }
}
