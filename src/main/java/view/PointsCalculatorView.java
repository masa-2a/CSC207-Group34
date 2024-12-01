package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.points_calculator.PointsCalculatorController;
import interface_adapter.points_calculator.PointsCalculatorState;
import interface_adapter.points_calculator.PointsCalculatorViewModel;

/**
 * The view for the Points Calculator.
 */
public class PointsCalculatorView extends JPanel implements ActionListener, PropertyChangeListener {

    // Constants
    private static final String FONT_NAME = "Arial";
    private static final int TITLE_FONT_SIZE = 24;
    private static final int MESSAGE_FONT_SIZE = 18;
    private static final int BUTTON_FONT_SIZE = 16;

    private final String viewName = "PointsCalculatorView";

    private final PointsCalculatorViewModel pointsCalculatorViewModel;
    private PointsCalculatorController pointsCalculatorController;
    private final JButton toMenu;

    public PointsCalculatorView(PointsCalculatorViewModel pointsCalculatorViewModel) {
        this.pointsCalculatorViewModel = pointsCalculatorViewModel;
        this.pointsCalculatorViewModel.addPropertyChangeListener(this);

        // Create the main panel for the view
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Title label
        final JLabel title = new JLabel(PointsCalculatorViewModel.TITLE_LABEL);
        title.setFont(new Font(FONT_NAME, Font.BOLD, TITLE_FONT_SIZE));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        // Image label
        final ImageIcon image = new ImageIcon(pointsCalculatorViewModel.getState().getImagePath());
        final JLabel imageLabel = new JLabel(image);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(imageLabel);

        // Points message label
        final JLabel pointsMessage = new JLabel(pointsCalculatorViewModel.getState().getMessage());
        pointsMessage.setFont(new Font(FONT_NAME, Font.PLAIN, MESSAGE_FONT_SIZE));
        pointsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(pointsMessage);

        // Create the toMenu button
        toMenu = new JButton(PointsCalculatorViewModel.TO_MENU_BUTTON_LABEL);
        toMenu.setFont(new Font(FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE));
        toMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        toMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
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
            final PointsCalculatorState newState = (PointsCalculatorState) evt.getNewValue();
            updateView(newState);
        }
    }

    private void updateView(PointsCalculatorState state) {
        // Clear the existing components before adding the new ones
        this.removeAll();

        // Create a new main panel to update the view
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Title label
        final JLabel title = new JLabel(PointsCalculatorViewModel.TITLE_LABEL);
        title.setFont(new Font(FONT_NAME, Font.BOLD, TITLE_FONT_SIZE));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        // Image label
        final ImageIcon image = new ImageIcon(state.getImagePath());
        final JLabel imageLabel = new JLabel(image);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(imageLabel);

        // Points message label
        final JLabel pointsMessage = new JLabel(pointsCalculatorViewModel.getState().getMessage());
        pointsMessage.setFont(new Font(FONT_NAME, Font.PLAIN, MESSAGE_FONT_SIZE));
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
