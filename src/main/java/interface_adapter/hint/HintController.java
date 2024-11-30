package interface_adapter.hint;

import use_case.hint.HintInputBoundary;
import use_case.hint.HintInputData;

public class HintController {
    private final HintInputBoundary hintInteractor;

    public HintController(HintInputBoundary hintInteractor) {
        this.hintInteractor = hintInteractor;
    }

    /**
     * Executes the Points Calculator Use Case.
     * @param country the country the user is trying to guess.
     */
    public void execute(String country) {
        final HintInputData pointsCalculatorInputData =
                new HintInputData(country);

        hintInteractor.execute(pointsCalculatorInputData);
    }

}
