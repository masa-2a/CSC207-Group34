package interface_adapter.hint;

import interface_adapter.ViewManagerModel;
import interface_adapter.hint.HintViewModel;
import use_case.hint.HintOutputData;

public class HintPresenter {
    private final HintViewModel hintViewModel;
    private final ViewManagerModel viewManagerModel;

    public HintPresenter(HintViewModel hintViewModel, ViewManagerModel viewManagerModel) {
        this.hintViewModel = hintViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view for the PointsCalculator Use Case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(HintOutputData outputData) {




    }
}
