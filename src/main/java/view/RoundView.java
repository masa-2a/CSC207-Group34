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

    public RoundView (RoundViewModel roundViewModel){
        this.roundViewModel = roundViewModel;
        this.roundViewModel.addPropertyChangeListener(this);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel(RoundViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);

    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

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

    }

    public String getViewName() {
        return viewName;
    }

    public void setRoundController(RoundController roundController) {
        this.roundController = roundController;
    }
}
