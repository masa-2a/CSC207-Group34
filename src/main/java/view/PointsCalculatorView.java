package view;

import interface_adapter.points_calculator.PointsCalculatorController;
import interface_adapter.points_calculator.PointsCalculatorState;
import interface_adapter.points_calculator.PointsCalculatorViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PointsCalculatorView  extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "PointsCalculatorView";

    private final PointsCalculatorViewModel pointsCalculatorViewModel;
    private PointsCalculatorController pointsCalculatorController;
    private final JButton toMenu;

    public PointsCalculatorView(PointsCalculatorViewModel pointsCalculatorViewModel) {
        this.pointsCalculatorViewModel = pointsCalculatorViewModel;
        this.pointsCalculatorViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel(PointsCalculatorViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        ImageIcon image = new ImageIcon(pointsCalculatorViewModel.getState().getImagePath());
        final JLabel imageLabel = new JLabel(image);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        toMenu = new JButton(PointsCalculatorViewModel.TO_MENU_BUTTON_LABEL);
        buttons.add(toMenu);

        toMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pointsCalculatorController.switchToMenuView();
            }
        });
        this.add(title);
        this.add(imageLabel);
        this.add(buttons);
    }


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
        if ("Points Calculator State Update".equals(evt.getPropertyName())) {
            PointsCalculatorState newState = (PointsCalculatorState) evt.getNewValue();
            updateView(newState);
        }


    }

    private void updateView(PointsCalculatorState state) {
        removeAll();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel(PointsCalculatorViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        ImageIcon image = new ImageIcon(pointsCalculatorViewModel.getState().getImagePath());
        final JLabel imageLabel = new JLabel(image);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        buttons.add(toMenu);
        revalidate();
        repaint();

    }
    public void setPointsCalculatorController(PointsCalculatorController pointsCalculatorController) {
        this.pointsCalculatorController = pointsCalculatorController;
    }

    public String getViewName() {
        return viewName;
    }

}
