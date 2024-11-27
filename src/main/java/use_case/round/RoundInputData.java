package use_case.round;

import use_case.map2d.Map2DInputData;

public class RoundInputData {
    private final Map2DInputData map2DInputData;

    public RoundInputData(int width, int height, double latitude, double longitude,
                          int zoom, double guessLat, double guessLong, double answerLat,
                          double answerLong, boolean guessed, boolean answered) {
        this.map2DInputData = new Map2DInputData(width, height, latitude, longitude,
                zoom, guessLat, guessLong, answerLat, answerLong, guessed, answered);
    }

    public Map2DInputData getMap2DInputData() {
        return map2DInputData;
    }
}