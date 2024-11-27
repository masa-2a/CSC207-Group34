package use_case.round;

import use_case.map2d.Map2DOutputData;

public class RoundOutputData {
    private final Map2DOutputData map2DOutputData;

    public RoundOutputData (Map2DOutputData map2DOutputData) {
        this.map2DOutputData = map2DOutputData;
    }

    public Map2DOutputData getMap2DOutputData() {
        return map2DOutputData;
    }
}
