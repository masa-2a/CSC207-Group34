package view;

import interface_adapter.round.RoundController;
import interface_adapter.round.RoundState;
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
    private final JButton startRound;
    private JLabel imageLabel;
//    private final JButton submitGuess;

    public RoundView (RoundViewModel roundViewModel){
        this.roundViewModel = roundViewModel;
        this.roundViewModel.addPropertyChangeListener(this);

        final JPanel buttons = new JPanel();
        startRound = new JButton("Start Round");
        startRound.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(startRound);
        // ActionListener for the mapButton and will execute the code below.
        startRound.addActionListener(this);


        // Initial image setup
        this.imageLabel = new JLabel();
        updateImage(roundViewModel.getMapImagePath());

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
        System.out.println("Click " + e.getActionCommand());
        if (e.getSource().equals(startRound)) {
            // Simulate updating the map image path

            roundController.execute();

        }
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
        if ("mapImagePath".equals(evt.getPropertyName())) {
            updateImage(((RoundState) evt.getNewValue()).getMapImagePath());
        }
    }

    private void updateImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().
                    getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            imageLabel.setIcon(imageIcon);
        } else {
            String origImagePath = "src/main/resources/static_map_original.png";
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(origImagePath).getImage().
                    getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            imageLabel.setIcon(imageIcon);
        }
        this.revalidate();
        this.repaint();
    }

    public String getViewName() {
        return viewName;
    }

    public void setRoundController(RoundController roundController) {
        this.roundController = roundController;
    }

    public RoundController getRoundController() {
        return roundController;
    }
}
