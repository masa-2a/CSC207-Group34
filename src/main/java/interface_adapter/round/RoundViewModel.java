package interface_adapter.round;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Round Use Case.
 */
public class RoundViewModel extends ViewModel<RoundState> {
    public static final String TITLE_LABEL = "Round View";

    /**
     * Constructor for the RoundViewModel.
     */
    public RoundViewModel() {
        super(TITLE_LABEL);
        setState(new RoundState(TITLE_LABEL));
    }

    /**
     * Sets the countdown timer.
     */
    public void setCountdownTimer() {
        firePropertyChanged("Countdown Timer Updated");
    }

    /**
     * Sets the hint status.
     */
    public void setHint() {
        firePropertyChanged("Hints Updated");
    }

}
