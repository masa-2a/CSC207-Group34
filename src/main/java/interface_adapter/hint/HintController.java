package interface_adapter.hint;

import use_case.hint.HintInputBoundary;
import use_case.hint.HintInputData;

public class HintController {
    private final HintInputBoundary hintInteractor;

    /**
     * Controller for Points Use Case
     *
     * @param hintInteractor
     */
    public HintController(HintInputBoundary hintInteractor) {
        this.hintInteractor = hintInteractor;
    }

    /**
     * Executes the Points Calculator Use Case.
     *
     * @param country the country the random location is located at.
     */
    public void execute(String country) {
        final HintInputData hintInputData = new HintInputData(country);

        hintInteractor.execute(hintInputData);
    }

}