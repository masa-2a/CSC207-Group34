package use_case.streetview;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

import javax.swing.*;
import java.awt.*;

public class MapApp extends JMapViewer {
    private static int FRAME_WIDTH;
    private static int FRAME_HEIGHT;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MapApp mapViewer = new MapApp();
            JFrame frame = new JFrame("JMapViewer Example");
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(null); // Use absolute positioning to fully control layout
            frame.setVisible(true);

            FRAME_WIDTH = frame.getWidth();
            FRAME_HEIGHT = frame.getHeight();

            mapViewer.setBounds(0, 0, FRAME_WIDTH / 2, FRAME_HEIGHT); // Cover left half
            frame.add(mapViewer);

            mapViewer.setScrollWrapEnabled(true);
            mapViewer.setZoomControlsVisible(true);
            mapViewer.setZoomButtonStyle(ZOOM_BUTTON_STYLE.HORIZONTAL);

            mapViewer.setTileSource(mapViewer.tileSource);

            System.out.println("Frame dimensions: " + FRAME_WIDTH + "x" + FRAME_HEIGHT);
        });
    }
}
