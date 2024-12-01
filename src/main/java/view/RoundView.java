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
    private final JButton submitGuess;
    private final JLabel timerLabel;
    private final JButton showHint;
    private final JLabel hintLabel;

    public RoundView (RoundViewModel roundViewModel){
        this.roundViewModel = roundViewModel;
        this.roundViewModel.addPropertyChangeListener(this);
        this.setBackground(new Color(219, 229, 232));
        roundViewModel.getState().setHintsUsed(0);

        final JPanel buttons = new JPanel();
        buttons.setBackground(new Color(219, 229, 232));
        startRound = new JButton("Start Round");
        startRound.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(startRound);
// ActionListener for the mapButton and will execute the code below.
        startRound.addActionListener(this);

        submitGuess = new JButton("Submit Guess");
        submitGuess.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitGuess.setVisible(false); // Initially hidden
        buttons.add(submitGuess);
        submitGuess.addActionListener(this);

// Timer label setup
        timerLabel = new JLabel("Time Left: 00:00");
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerLabel.setVisible(false);
        timerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        timerLabel.setFont(new Font("Serif", Font.BOLD, 20));

// Show hints buttons
        showHint = new JButton("Show Hint");
        showHint.setAlignmentX(Component.CENTER_ALIGNMENT);
        showHint.setVisible(false); // Initially hidden
        buttons.add(showHint);
        showHint.addActionListener(this);
// Hint Text Label
        hintLabel = new JLabel("Hint: ");
        hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        hintLabel.setVisible(false);
        hintLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        hintLabel.setFont(new Font("Serif", Font.BOLD, 20));


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        final JLabel title = new JLabel(RoundViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);
        this.add(buttons);
        this.add(timerLabel);
        this.add(Box.createVerticalGlue()); // Push content above to center this
        this.add(hintLabel);
        this.add(Box.createVerticalGlue()); // Push content above to center this
// Add the timer label to the view


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

            startRound.setVisible(false);
            submitGuess.setVisible(true);
            timerLabel.setVisible(true);
            showHint.setVisible(true);
            roundController.execute();

        }
        if (e.getSource().equals(submitGuess)) {
            // Buttons to submit the guess

            roundController.submitGuess(
                    roundViewModel.getState().getGoalLatitude(),
                    roundViewModel.getState().getGoalLongitude(),
                    roundViewModel.getState().getGuessedLatitude(),
                    roundViewModel.getState().getGuessedLongitude(),
                    roundViewModel.getState().getCountry(),
                    roundViewModel.getState().getHintsUsed());
        }
        if (e.getSource().equals(showHint)) {
            hintLabel.setVisible(true);
            System.out.println("THIS IS RUN");
            roundViewModel.getState().incrementHintsUsed();
            System.out.println(roundViewModel.getState().getHintsUsed());
            roundController.showHint(roundViewModel.getState().getCountry());
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
        if ("Countdown Timer Updated".equals(evt.getPropertyName())) {
            String timeLeft = roundViewModel.getState().getTimeLeft();
            timerLabel.setText("Time Left: " + timeLeft);
            this.revalidate();
            this.repaint();
        }
        if ("Hints Updated".equals(evt.getPropertyName())) {
            String hint = roundViewModel.getState().getHint();
            hintLabel.setText(hint);
            this.revalidate();
            this.repaint();
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
