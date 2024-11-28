package use_case.round;

import use_case.map2d.Map2DOutputData;

public class RoundOutputData {
    private final double guessLat;
    private final double guessLong;

    public RoundOutputData (double guessLat,double guessLong) {
        this.guessLat = guessLat;
        this.guessLong = guessLong;
    }

    public double getGuessLat() {
        return guessLat;
    }

    public double getGuessLong() {
        return guessLong;
    }
}
