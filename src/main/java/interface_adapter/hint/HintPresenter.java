package interface_adapter.hint;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MenuViewModel;
import use_case.hint.HintOutputBoundary;
import use_case.hint.HintOutputData;

public class HintPresenter implements HintOutputBoundary  {
    private final HintViewModel hintViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuViewModel menuViewModel;

    /**
     * Presenter for Points Calculator
     *
     * @param hintViewModel
     * @param viewManagerModel
     * @param menuViewModel
     */
    public HintPresenter(HintViewModel hintViewModel, ViewManagerModel viewManagerModel, MenuViewModel menuViewModel) {
        this.hintViewModel = hintViewModel;
        this.viewManagerModel = viewManagerModel;
        this.menuViewModel = menuViewModel;
    }

    /**
     * Prepares the success view for the Hint Use Case.
     *
     * @param hintOutputData the output data
     */
    @Override
    public void prepareSuccessView(HintOutputData hintOutputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
