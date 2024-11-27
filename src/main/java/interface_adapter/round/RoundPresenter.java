package interface_adapter.round;

import interface_adapter.ViewManagerModel;
import use_case.round.RoundOutputBoundary;
import use_case.round.RoundOutputData;
import use_case.hint.HintOutputBoundary;
import use_case.hint.HintOutputData;

public class RoundPresenter implements RoundOutputBoundary {

    private final RoundViewModel roundViewModel;
    private final ViewManagerModel viewManagerModel;

    public RoundPresenter(RoundViewModel roundViewModel,
                          ViewManagerModel viewManagerModel) {
        this.roundViewModel = roundViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Switches to the Map View.
     */
    @Override
    public void presentMapData(RoundOutputData roundOutputData) {
        viewManagerModel.setState(roundViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
