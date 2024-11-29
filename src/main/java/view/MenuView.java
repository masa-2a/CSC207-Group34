package view;

import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.main_menu.MenuController;
import interface_adapter.main_menu.MenuState;
import interface_adapter.main_menu.MenuViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

public class MenuView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Menu View";

    private final MenuViewModel menuViewModel;
    private final JButton newRound;
    private final JButton leaderboard;
    private final JButton logout;
    private MenuController menuController;

    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        this.menuViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel(MenuViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon logo = new ImageIcon("src/main/resources/MapMasterLogo.png");
        Image image = logo.getImage(); // transform it
        Image newimg = image.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH); // scale it smoothly
        ImageIcon logoScaled = new ImageIcon(newimg);  // assign to a new ImageIcon instance

        final JLabel imageLabel = new JLabel(logoScaled);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        final JPanel buttons = new JPanel();
        newRound = new JButton(MenuViewModel.NEW_ROUND_BUTTON_LABEL);
        newRound.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(newRound);
        leaderboard = new JButton(MenuViewModel.LEADERBOARD);
        leaderboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(leaderboard);
        logout = new JButton(MenuViewModel.TO_LOGOUT_BUTTON_LABEL);
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(logout);

        newRound.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        menuController.switchToNewRoundView();
                    }
                }
        );
        leaderboard.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        menuController.switchToLeaderboardView();
                    }
                }
        );
        logout.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        menuController.switchToLogoutView();
                    }
                }
        );

        this.add(title);
//        this.add(greeting);
        this.add(imageLabel);
        this.add(buttons);

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
        if ("User updated".equals(evt.getPropertyName())) {
            MenuState newState = (MenuState) evt.getNewValue();
            updateView(newState);
        }

    }

    public void updateView(MenuState newState) {
        String currentUser = newState.getCurrentUsername();
        String message = "Welcome " + currentUser + "!";
        final JLabel greeting = new JLabel(message);
        greeting.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Remove the old greeting label if it exists
        if (this.getComponentCount() > 3) {
            this.remove(3);
        }

        // Add the new greeting label
        this.add(greeting, 3);
        this.revalidate();
        this.repaint();
    }

    public String getViewName() {
        return viewName;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

}
