package use_case.hint;

public class HintTest {
    public static void main(String[] args) {
        HintInteractor hintInteractor = new HintInteractor();

        HintInputData hintInputData = new HintInputData("India",
                "src/main/resources/country_data.json");
        System.out.println(hintInteractor.execute(hintInputData).getHint());
        System.out.println(hintInteractor.execute(hintInputData).getHint());
        System.out.println(hintInteractor.execute(hintInputData).getHint());
        System.out.println(hintInteractor.execute(hintInputData).getHint());
    }
}
