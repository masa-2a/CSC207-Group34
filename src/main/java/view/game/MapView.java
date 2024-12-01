package view.game;

import interface_adapter.streetview_map.StreetViewMapController;

/**
 * View for the Map Use Case.
 */
public class MapView {
    private StreetViewMapController streetViewMapController;

    public MapView(StreetViewMapController streetViewMapController) {
        this.streetViewMapController = streetViewMapController;
    }

    public void setStreetViewMapController(StreetViewMapController streetViewMapController) {
        this.streetViewMapController = streetViewMapController;
    }

    public StreetViewMapController getStreetViewMapController() {
        return streetViewMapController;
    }
}
