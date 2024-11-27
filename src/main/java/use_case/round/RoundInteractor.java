package use_case.round;

import use_case.map2d.Map2DInputBoundary;
import use_case.map2d.Map2DOutputData;

public class RoundInteractor implements RoundInputBoundary {
    private final RoundOutputBoundary roundPresenter;

    private final Map2DInputBoundary map2DInteractor;

    public RoundInteractor(Map2DInputBoundary map2DInteractor, RoundOutputBoundary roundPresenter) {
        this.map2DInteractor = map2DInteractor;
        this.roundPresenter = roundPresenter;
    }

    @Override
    public void execute(RoundInputData roundInputData) {
        Map2DOutputData map2DOutputData = map2DInteractor.execute(roundInputData.getMap2DInputData());
        RoundOutputData roundOutputData = new RoundOutputData(map2DOutputData);
        roundPresenter.presentMapData(roundOutputData);
    }



}
