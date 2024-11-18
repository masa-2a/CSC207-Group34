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

    @Override
    public void setZoomButtonStyle(JMapViewer.ZOOM_BUTTON_STYLE style) {
        int buttonX = FRAME_WIDTH / 2 - 60; // Position near the edge of the map
        int buttonY = FRAME_HEIGHT - 100; // Near the bottom edge
        int buttonSize = 40; // Button size

        zoomSlider.setBounds(0, 0, 0, 0); // Hide the slider
        zoomOutButton.setBounds(buttonX, buttonY, buttonSize, buttonSize);
        zoomInButton.setBounds(buttonX - buttonSize - 10, buttonY, buttonSize, buttonSize);

        repaint();
    }
}
