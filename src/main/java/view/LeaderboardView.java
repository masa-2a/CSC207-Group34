package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;

/**
 * The LeaderboardView class is a JPanel that displays the leaderboard.
 */
public class LeaderboardView extends JPanel implements ActionListener, PropertyChangeListener {

    private static final int TITLE_FONT_SIZE = 40;
    private static final int BUTTON_FONT_SIZE = 16;
    private static final int USER_PANEL_WIDTH = 350;
    private static final int USER_PANEL_HEIGHT = 80;
    private static final int BORDER_THICKNESS = 2;
    private static final int RANK_LABEL_FONT_SIZE = 36;
    private static final int NAME_LABEL_FONT_SIZE = 24;
    private static final int POINTS_LABEL_FONT_SIZE = 24;
    private static final int RANK_LABEL_DIMENSION = 50;
    private static final int USER_DETAILS_WIDTH = 300;
    private static final int USER_DETAILS_HEIGHT = 50;
    private static final int SPACING_RIGID_AREA = 20;
    private static final int RANK_3 = 3;
    private static final String ARIAL = "Arial";

    private static final Color BACKGROUND_COLOR_MAIN = new Color(219, 229, 232);
    private static final Color BACKGROUND_COLOR_PANEL = new Color(219, 230, 234);
    private static final Color BACKGROUND_COLOR_DARK_BLUE = new Color(86, 135, 130);
    private static final Color BACKGROUND_COLOR_LIGHT_YELLOW = new Color(252, 252, 197);

    private final String viewName = "Leaderboard";
    private final LeaderboardViewModel leaderboardViewModel;
    private final JButton menu;

    private LeaderboardController leaderboardController;

    public LeaderboardView(LeaderboardViewModel leaderboardViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
        this.leaderboardViewModel.addPropertyChangeListener(this);
        this.setBackground(BACKGROUND_COLOR_PANEL);

        final LeaderboardState leaderboardState = leaderboardViewModel.getState();

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BACKGROUND_COLOR_MAIN);

        final JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setFont(new Font("Agency FB", Font.PLAIN, TITLE_FONT_SIZE));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, SPACING_RIGID_AREA)));

        final JPanel leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        leaderboardPanel.setBackground(BACKGROUND_COLOR_MAIN);

        leaderboardPanel.add(createUserPanel(1,
                leaderboardState.getFirstPlaceName(), leaderboardState.getFirstPlacePoints()));
        leaderboardPanel.add(createUserPanel(2,
                leaderboardState.getSecondPlaceName(), leaderboardState.getSecondPlacePoints()));
        leaderboardPanel.add(createUserPanel(RANK_3,
                leaderboardState.getThirdPlaceName(), leaderboardState.getThirdPlacePoints()));

        leaderboardPanel.add(Box.createRigidArea(new Dimension(0, SPACING_RIGID_AREA)));

        if (leaderboardState.getCurrentUserPlace() != null) {
            leaderboardPanel.add(createUserPanel(Integer.valueOf(leaderboardState.getCurrentUserPlace()),
                    LeaderboardViewModel.YOU_LABEL,
                    leaderboardState.getCurrentUserPoints()));
        }

        mainPanel.add(leaderboardPanel);
        mainPanel.setBackground(BACKGROUND_COLOR_MAIN);

        menu = new JButton(LeaderboardViewModel.BACK_TO_MENU_BUTTON);
        menu.setFont(new Font(ARIAL, Font.PLAIN, BUTTON_FONT_SIZE));
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.setBackground(BACKGROUND_COLOR_MAIN);
        menu.addActionListener(
                event -> leaderboardController.switchToMenuView()
        );
        mainPanel.add(Box.createRigidArea(new Dimension(0, SPACING_RIGID_AREA)));
        mainPanel.add(menu);

        final JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBackground(BACKGROUND_COLOR_MAIN);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);
        this.setVisible(true);
    }

    private JPanel createUserPanel(Integer rank, String name, int avgPoints) {
        final JPanel userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());
        userPanel.setPreferredSize(new Dimension(USER_PANEL_WIDTH, USER_PANEL_HEIGHT));
        userPanel.setMaximumSize(new Dimension(USER_PANEL_WIDTH, USER_PANEL_HEIGHT));
        userPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDER_THICKNESS));
        userPanel.setBackground(getBg(rank, BACKGROUND_COLOR_LIGHT_YELLOW, BACKGROUND_COLOR_DARK_BLUE));

        final JLabel rankLabel = new JLabel(String.valueOf(rank));
        rankLabel.setFont(new Font(ARIAL, Font.BOLD, RANK_LABEL_FONT_SIZE));
        rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rankLabel.setPreferredSize(new Dimension(RANK_LABEL_DIMENSION, RANK_LABEL_DIMENSION));

        final JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font(ARIAL, Font.BOLD, NAME_LABEL_FONT_SIZE));
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        final JLabel pointsLabel = new JLabel(String.valueOf(avgPoints));
        pointsLabel.setFont(new Font(ARIAL, Font.BOLD, POINTS_LABEL_FONT_SIZE));
        pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        final JPanel userDetails = new JPanel(new BorderLayout());
        userDetails.add(nameLabel, BorderLayout.WEST);
        userDetails.add(pointsLabel, BorderLayout.EAST);
        userDetails.setPreferredSize(new Dimension(USER_DETAILS_WIDTH, USER_DETAILS_HEIGHT));
        userDetails.setBackground(BACKGROUND_COLOR_MAIN);

        userPanel.add(rankLabel, BorderLayout.WEST);
        userPanel.add(userDetails, BorderLayout.CENTER);
        userPanel.add(pointsLabel, BorderLayout.EAST);
        return userPanel;
    }

    private static Color getBg(Integer rank, Color lightYellow, Color darkBlue) {
        Color result = getColor(rank, darkBlue);
        if (rank == 1) {
            result = lightYellow;
        }
        return result;
    }

    private static Color getColor(Integer rank, Color darkBlue) {
        final Color result;
        if (rank <= RANK_3) {
            result = darkBlue;
        }
        else {
            result = Color.WHITE;
        }
        return result;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("Leaderboard State Update".equals(evt.getPropertyName())) {
            final LeaderboardState newState = (LeaderboardState) evt.getNewValue();
            updateView(newState);
        }
    }

    private void updateView(LeaderboardState state) {
        // Update the leaderboard panel with new state
        removeAll();
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BACKGROUND_COLOR_MAIN);

        final JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setFont(new Font("Agency FB", Font.PLAIN, TITLE_FONT_SIZE));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBackground(BACKGROUND_COLOR_MAIN);
        mainPanel.add(title);

        mainPanel.add(Box.createRigidArea(new Dimension(0, SPACING_RIGID_AREA)));

        final JPanel leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));

        leaderboardPanel.add(createUserPanel(1, state.getFirstPlaceName(), state.getFirstPlacePoints()));
        leaderboardPanel.add(createUserPanel(2, state.getSecondPlaceName(), state.getSecondPlacePoints()));
        leaderboardPanel.add(createUserPanel(RANK_3, state.getThirdPlaceName(), state.getThirdPlacePoints()));

        leaderboardPanel.add(Box.createRigidArea(new Dimension(0, SPACING_RIGID_AREA)));
        if (state.getCurrentUserPlace() != null) {
            leaderboardPanel.add(createUserPanel(Integer.valueOf(state.getCurrentUserPlace()),
                    LeaderboardViewModel.YOU_LABEL, state.getCurrentUserPoints()));
        }
        leaderboardPanel.setBackground(BACKGROUND_COLOR_MAIN);
        mainPanel.add(leaderboardPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, SPACING_RIGID_AREA)));
        mainPanel.add(menu);

        final JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
        revalidate();
        repaint();
    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt the ActionEvent to react to
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the leaderboard controller.
     *
     * @param leaderboardController the leaderboard controller
     */
    public void setLeaderboardController(LeaderboardController leaderboardController) {
        this.leaderboardController = leaderboardController;
        leaderboardController.execute(leaderboardViewModel.getState().getCurrentUsername());
    }

}
