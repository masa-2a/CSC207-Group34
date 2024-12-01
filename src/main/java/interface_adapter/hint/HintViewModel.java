package interface_adapter.hint;

import interface_adapter.ViewModel;

/**
 * The View Model for the Hint View.
 */
public class HintViewModel extends ViewModel<HintState> {

    public HintViewModel() {
        super("hint");
        setState(new HintState());
    }

}

