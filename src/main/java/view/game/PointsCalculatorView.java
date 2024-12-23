package view.game;

import java.awt.*;
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
    public static final int B = 232;
    public static final int G = 229;
    public static final int R = 219;
    public static final int SIZE = 40;

    private static final int MESSAGE_FONT_SIZE = 18;
    private static final int WIDTH_HEIGHT = 300;

    private static final String FONT_NAME = "Arial";
    private final String viewName = "PointsCalculatorView";

    private final PointsCalculatorViewModel pointsCalculatorViewModel;
    private PointsCalculatorController pointsCalculatorController;
    private final JButton toMenu;

    public PointsCalculatorView(PointsCalculatorViewModel pointsCalculatorViewModel) {
        this.pointsCalculatorViewModel = pointsCalculatorViewModel;
        this.pointsCalculatorViewModel.addPropertyChangeListener(this);

        this.setBackground(new Color(R, G, B));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title label
        final JLabel title = new JLabel(PointsCalculatorViewModel.TITLE_LABEL);
        title.setFont(new Font("Agency FB", Font.BOLD, SIZE));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Image label

        // ImageIcon image = new ImageIcon(pointsCalculatorViewModel.getState().getImagePath());
        final ImageIcon map = new ImageIcon("src/main/resources/static_map.png");
        final Image image = map.getImage();
        final Image newimg = image.getScaledInstance(WIDTH_HEIGHT, WIDTH_HEIGHT, Image.SCALE_SMOOTH);
        final ImageIcon imageScaled = new ImageIcon(newimg);

        final JLabel imageLabel = new JLabel(imageScaled);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Points message label
        final JLabel pointsMessage = new JLabel(pointsCalculatorViewModel.getState().getMessage());
        pointsMessage.setFont(new Font(FONT_NAME, Font.PLAIN, MESSAGE_FONT_SIZE));
        pointsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the toMenu button
        final JPanel button = new JPanel();
        button.setBackground(new Color(R, G, B));
        toMenu = new JButton(PointsCalculatorViewModel.TO_MENU_BUTTON_LABEL);

        toMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.add(toMenu);
        toMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                pointsCalculatorController.switchToMenuView();
            }
        });
        this.add(title);
        this.add(pointsMessage);
        this.add(imageLabel);
        this.add(button);
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

        final JLabel title = new JLabel(PointsCalculatorViewModel.TITLE_LABEL);
        title.setFont(new Font("Agency FB", Font.BOLD, SIZE));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Image label
        // ImageIcon map = new ImageIcon(pointsCalculatorViewModel.getState().getImagePath());
        final ImageIcon map = new ImageIcon("src/main/resources/static_map.png");
        final Image image = map.getImage();
        final Image newimg = image.getScaledInstance(WIDTH_HEIGHT, WIDTH_HEIGHT, Image.SCALE_SMOOTH);
        final ImageIcon imageScaled = new ImageIcon(newimg);

        final JLabel imageLabel = new JLabel(imageScaled);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel pointsMessage = new JLabel(pointsCalculatorViewModel.getState().getMessage());
        pointsMessage.setFont(new Font("Arial", Font.PLAIN, MESSAGE_FONT_SIZE));
        pointsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the toMenu button
        final JPanel button = new JPanel();
        button.setBackground(new Color(R, G, B));

        toMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.add(toMenu);
        toMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                pointsCalculatorController.switchToMenuView();
            }
        });
        this.add(title);
        this.add(pointsMessage);
        this.add(imageLabel);
        this.add(button);

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
