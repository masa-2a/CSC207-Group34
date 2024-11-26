package use_case.round;

public class RoundInteractor implements RoundInputBoundary {
    private final RoundOutputBoundary roundOutputBoundary;
    public RoundInteractor(RoundOutputBoundary roundOutputBoundary) {
        this.roundOutputBoundary = roundOutputBoundary;
    }

    @Override
    public void execute(RoundInputData roundInputData) {
        return;
    }
}
