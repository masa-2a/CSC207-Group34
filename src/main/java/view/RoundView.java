package view;

import interface_adapter.round.RoundController;
import interface_adapter.round.RoundViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RoundView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Round View";

    private final RoundViewModel roundViewModel;
    private RoundController roundController;

    private final JButton mapButton;

    public RoundView (RoundViewModel roundViewModel){
        this.roundViewModel = roundViewModel;
        this.roundViewModel.addPropertyChangeListener(this);

        final JPanel buttons = new JPanel();

        mapButton = new JButton("Show Map");

        mapButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(mapButton);

        // ActionListener for the mapButton and will execute the code below.
        mapButton.addActionListener(this);

        // Adding the Static Map image to this View
        String imagePath = "src/main/resources/static_map.png"; // Replace with your image path
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(imageIcon);



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel(RoundViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);
        this.add(buttons);
        this.add(imageLabel); // Add the image label to the view

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Map Button Clicked");
        int width = 600;
        int height = 400;
        double latitude = 0;
        double longitude = 0;
        int zoom = 2;
        double guessLat = 0;
        double guessLong = 0;
        double answerLat = 0;
        double answerLong = 0;
        boolean guessed = false;
        boolean answered = false;
        roundController.execute(width, height, latitude, longitude,
                zoom, guessLat, guessLong, answerLat, answerLong, guessed, answered);
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Round State")) {
            // Update the view based on the new state, such as setting the user's name
            System.out.println(evt.getPropertyName());
            // Update any labels or fields with the current state
        }
        else if (evt.getPropertyName().equals("imagePath")) {
            String newImagePath = (String) evt.getNewValue();
            ImageIcon newImageIcon = new ImageIcon(new ImageIcon(newImagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel(newImageIcon);
            this.removeAll();
            this.add(imageLabel);
            this.revalidate();
            this.repaint();
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setRoundController(RoundController roundController) {
        this.roundController = roundController;
    }

}
