package interface_adapter.round;

import interface_adapter.ViewModel;

public class RoundViewModel extends ViewModel<RoundState> {
    public static final String TITLE_LABEL = "Round View";

    public RoundViewModel() {
        super("Round View");
        setState(new RoundState("Round View"));
    }

    public void setCountdownTimer() {
        firePropertyChanged("Countdown Timer Updated");
    }

    public void setHint() {
        firePropertyChanged("Hints Updated");
    }

}
