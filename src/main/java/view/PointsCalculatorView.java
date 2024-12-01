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
        this.setBackground(new Color(219, 229, 232));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title label
        final JLabel title = new JLabel(PointsCalculatorViewModel.TITLE_LABEL);
        title.setFont(new Font("Agency FB", Font.BOLD, 40));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Image label
        //ImageIcon image = new ImageIcon(pointsCalculatorViewModel.getState().getImagePath());
        ImageIcon map = new ImageIcon("src/main/resources/static_map.png");
        Image image = map.getImage();
        Image newimg = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon imageScaled = new ImageIcon(newimg);

        JLabel imageLabel = new JLabel(imageScaled);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Points message label
//        String message = "You scored " + pointsCalculatorViewModel.getState().getPointsEarned() + " points!";

        JLabel pointsMessage = new JLabel(pointsCalculatorViewModel.getState().getMessage());
        pointsMessage.setFont(new Font("Arial", Font.PLAIN, 18));
        pointsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the toMenu button
        final JPanel button = new JPanel();
        button.setBackground(new Color(219, 229, 232));
        toMenu = new JButton(PointsCalculatorViewModel.TO_MENU_BUTTON_LABEL);
        toMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.add(toMenu);
        toMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
            PointsCalculatorState newState = (PointsCalculatorState) evt.getNewValue();
            updateView(newState);
        }
    }

    private void updateView(PointsCalculatorState state) {
        // Clear the existing components before adding the new ones
        this.removeAll();

        final JLabel title = new JLabel(PointsCalculatorViewModel.TITLE_LABEL);
        title.setFont(new Font("Agency FB", Font.BOLD, 40));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Image label
        //ImageIcon map = new ImageIcon(pointsCalculatorViewModel.getState().getImagePath());
        ImageIcon map = new ImageIcon("src/main/resources/static_map.png");
        Image image = map.getImage();
        Image newimg = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon imageScaled = new ImageIcon(newimg);

        JLabel imageLabel = new JLabel(imageScaled);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Points message label
//        String message = "You scored " + pointsCalculatorViewModel.getState().getPointsEarned() + " points!";

        JLabel pointsMessage = new JLabel(pointsCalculatorViewModel.getState().getMessage());
        pointsMessage.setFont(new Font("Arial", Font.PLAIN, 18));
        pointsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the toMenu button
        final JPanel button = new JPanel();
        button.setBackground(new Color(219, 229, 232));

        toMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.add(toMenu);
        toMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
