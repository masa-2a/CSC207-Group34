package use_case.hint;

/**
 * This class contains the input data required for the hint generation.
 */
public class HintTest {

    /**
     * Main method to test the hint generation.
     * @param args the arguments
     */
    public static void main(String[] args) {

        final HintInteractor hintInteractor = new HintInteractor();

        final HintInputData hintInputData = new HintInputData("India",
                "src/main/resources/country_data.json");
        System.out.println(hintInteractor.execute(hintInputData).getHint());
        System.out.println(hintInteractor.execute(hintInputData).getHint());
        System.out.println(hintInteractor.execute(hintInputData).getHint());
        System.out.println(hintInteractor.execute(hintInputData).getHint());
    }
}
