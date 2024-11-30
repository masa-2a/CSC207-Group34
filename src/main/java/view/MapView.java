package view;

import interface_adapter.streetview_map.StreetViewMapController;

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
