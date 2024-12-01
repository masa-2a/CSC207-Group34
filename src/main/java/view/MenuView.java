package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.main_menu.MenuController;
import interface_adapter.main_menu.MenuState;
import interface_adapter.main_menu.MenuViewModel;

/**
 * The view for the main menu.
 */
public class MenuView extends JPanel implements ActionListener, PropertyChangeListener {

    // Constants for magic numbers
    private static final int TITLE_FONT_SIZE = 40;
    private static final int GREETING_FONT_SIZE = 20;
    private static final int LOGO_WIDTH = 250;
    private static final int LOGO_HEIGHT = 250;
    private static final Color BACKGROUND_COLOR = new Color(219, 229, 232);

    private final String viewName = "Menu View";

    private final MenuViewModel menuViewModel;
    private final JButton newRound;
    private final JButton leaderboard;
    private final JButton logout;
    private MenuController menuController;
    private LeaderboardController leaderboardController;
    private JLabel greeting;

    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        this.menuViewModel.addPropertyChangeListener(this);
        this.setBackground(BACKGROUND_COLOR);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel(MenuViewModel.TITLE_LABEL);
        title.setFont(new Font("Agency FB", Font.PLAIN, TITLE_FONT_SIZE));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        greeting = new JLabel("Welcome, Guest!");
        greeting.setFont(new Font("Agency FB", Font.PLAIN, GREETING_FONT_SIZE));
        greeting.setAlignmentX(Component.CENTER_ALIGNMENT);

        final ImageIcon logo = new ImageIcon("src/main/resources/MapMasterLogo.png");
        final Image image = logo.getImage();
        final Image newimg = image.getScaledInstance(LOGO_WIDTH, LOGO_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        final ImageIcon logoScaled = new ImageIcon(newimg);

        final JLabel imageLabel = new JLabel(logoScaled);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        buttons.setBackground(BACKGROUND_COLOR);
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
                    public void actionPerformed(ActionEvent event) {
                        menuController.switchToNewRoundView();
                    }
                }
        );
        leaderboard.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        final MenuState menuState = new MenuState();
                        menuController.switchToLeaderboardView();
                        leaderboardController.execute(menuState.getCurrentUsername());
                    }
                }
        );
        logout.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        menuController.switchToLogoutView();
                    }
                }
        );

        this.add(title);
        this.add(greeting);
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
            final MenuState newState = (MenuState) evt.getNewValue();
            updateView(newState);
        }

    }

    /**
     * Updates the view with the new state.
     *
     * @param newState the new state
     */
    public void updateView(MenuState newState) {
        final String currentUser = newState.getCurrentUsername();
        greeting.setText("Welcome " + currentUser + "!");
        this.revalidate();
        this.repaint();
    }

    public String getViewName() {
        return viewName;
    }

    public void setLeaderboardController(LeaderboardController leaderboardController) {
        this.leaderboardController = leaderboardController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }
}
