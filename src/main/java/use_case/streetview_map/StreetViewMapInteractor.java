package use_case.streetview_map;

public class StreetViewMapInteractor implements StreetViewMapInputBoundary {
    private final StreetViewMapOutputBoundary outputBoundary;

    public StreetViewMapInteractor(StreetViewMapOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void printCoordinates(double totalDistance) {
        StreetViewMapOutputData outputData = new StreetViewMapOutputData(totalDistance);
        outputBoundary.present(outputData);
    }
}
