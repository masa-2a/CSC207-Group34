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

public class PointsCalculatorView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "PointsCalculatorView";

    private final PointsCalculatorViewModel pointsCalculatorViewModel;
    private PointsCalculatorController pointsCalculatorController;
    private final JButton toMenu;

    public PointsCalculatorView(PointsCalculatorViewModel pointsCalculatorViewModel) {
        this.pointsCalculatorViewModel = pointsCalculatorViewModel;
        this.pointsCalculatorViewModel.addPropertyChangeListener(this);

        // Create the main panel for the view
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Title label
        JLabel title = new JLabel(PointsCalculatorViewModel.TITLE_LABEL);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        // Image label
        ImageIcon image = new ImageIcon(pointsCalculatorViewModel.getState().getImagePath());
        JLabel imageLabel = new JLabel(image);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(imageLabel);

        // Points message label
//        String message = "You scored " + pointsCalculatorViewModel.getState().getPointsEarned() + " points!";

        JLabel pointsMessage = new JLabel(pointsCalculatorViewModel.getState().getMessage());
        pointsMessage.setFont(new Font("Arial", Font.PLAIN, 18));
        pointsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(pointsMessage);

        // Create the toMenu button
        toMenu = new JButton(PointsCalculatorViewModel.TO_MENU_BUTTON_LABEL);
        toMenu.setFont(new Font("Arial", Font.PLAIN, 16));
        toMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        toMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pointsCalculatorController.switchToMenuView();
            }
        });
        mainPanel.add(toMenu);
        this.add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Empty method, as no other actions are being handled here
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("Points Calculator State Update".equals(evt.getPropertyName())) {
            PointsCalculatorState newState = (PointsCalculatorState) evt.getNewValue();
            updateView(newState);
        }
    }

    private void updateView(PointsCalculatorState state) {
        // Clear the existing components before adding the new ones
        this.removeAll();

        // Create a new main panel to update the view
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Title label
        JLabel title = new JLabel(PointsCalculatorViewModel.TITLE_LABEL);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        // Image label
        ImageIcon image = new ImageIcon(state.getImagePath());
        JLabel imageLabel = new JLabel(image);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(imageLabel);

        // Points message label
        JLabel pointsMessage = new JLabel(pointsCalculatorViewModel.getState().getMessage());
        pointsMessage.setFont(new Font("Arial", Font.PLAIN, 18));
        pointsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(pointsMessage);

        // Add the toMenu button
        mainPanel.add(toMenu);
        this.add(mainPanel);

        // Revalidate and repaint to update the view
        this.revalidate();
        this.repaint();
    }

    public void setPointsCalculatorController(PointsCalculatorController pointsCalculatorController) {
        this.pointsCalculatorController = pointsCalculatorController;
    }

    public String getViewName() {
        return viewName;
    }
}
