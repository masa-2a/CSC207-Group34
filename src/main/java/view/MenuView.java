package view;

import interface_adapter.main_menu.MenuController;
import interface_adapter.main_menu.MenuViewModel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class MenuView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Menu View";

    private final MenuViewModel menuViewModel;
    private final JButton newRound;
    private final JButton leaderboard;
    private final JButton logout;
    private MenuController menuController;

    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        menuViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel(MenuViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

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

//        newRound.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        menuController.switchToNewRoundView();
//                    }
//                }
//        );
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
        if (evt.getPropertyName().equals("menuState")) {
            // Update the view based on the new state, such as setting the user's name
           System.out.println(evt.getPropertyName());
            // Update any labels or fields with the current state
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

}
