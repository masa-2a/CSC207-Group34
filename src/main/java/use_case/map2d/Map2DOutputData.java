package use_case.map2d;

/**
 * Output data for the Map2D Use Case.
 */
public class Map2DOutputData {
    private final String mapPath;

    public Map2DOutputData(String mapPath) {
        this.mapPath = mapPath;
    }

    public String getMapPath() {
        return mapPath;
    }
}
