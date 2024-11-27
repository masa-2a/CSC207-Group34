package use_case.map2d;

public class Map2DInputData {
    private final int width;
    private final int height;
    private final double latitude;
    private final double longitude;
    private final int zoom;
    private final double guessLat;
    private final double guessLong;
    private final double answerLat;
    private final double answerLong;
    private final boolean guessed;
    private final boolean answered;

    public Map2DInputData(int width, int height, double latitude, double longitude,
                          int zoom, double guessLat, double guessLong, double answerLat,
                          double answerLong, boolean guessed, boolean answered) {
        this.width = width;
        this.height = height;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zoom = zoom;
        this.guessLat = guessLat;
        this.guessLong = guessLong;
        this.answerLat = answerLat;
        this.answerLong = answerLong;
        this.guessed = guessed;
        this.answered = answered;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getZoom() {
        return zoom;
    }

    public double getGuessLat() {
        return guessLat;
    }

    public double getGuessLong() {
        return guessLong;
    }

    public double getAnswerLat() {
        return answerLat;
    }

    public double getAnswerLong() {
        return answerLong;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public boolean isAnswered() {
        return answered;
    }
}