package use_case.streetview_map;

public class StreetViewMapInteractor implements StreetViewMapInputBoundary {
    private final StreetViewMapOutputBoundary streetViewPresenter;

    public StreetViewMapInteractor(StreetViewMapOutputBoundary streetViewPresenter) {
        this.streetViewPresenter = streetViewPresenter;
    }

    @Override
    public void execute(StreetViewMapInputData streetViewInputData) {
        final double[] userCoordinates = streetViewInputData.getUserCoordinates();
        final double[] goalCoordinates = streetViewInputData.getGoalCoordinates();

        StreetViewMapOutputData outputData = new StreetViewMapOutputData(userCoordinates, goalCoordinates);
        streetViewPresenter.present(outputData);
    }
}
