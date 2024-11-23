package use_case.map2d;

public class Map2DInputData {
    private final int width;
    private final int height;
    private final double latitude;
    private final double longitude;
    private final int zoom;

    public Map2DInputData(int width, int height, double latitude, double longitude, int zoom) {
        this.width = width;
        this.height = height;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zoom = zoom;
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
}
