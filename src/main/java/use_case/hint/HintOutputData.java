package use_case.hint;

public class HintOutputData {
    private String hint;
    private int hintsUsed;

    public HintOutputData(String hint, int hintsUsed) {
        this.setHint(hint);
        this.setHintsUsed(hintsUsed);
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getHintsUsed() {
        return hintsUsed;
    }

    public void setHintsUsed(int hintsUsed) {
        this.hintsUsed = hintsUsed;
    }
}
