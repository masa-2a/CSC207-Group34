package view;

import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LeaderboardView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Leaderboard";
    private final LeaderboardViewModel leaderboardViewModel;
    private final JButton menu;

    private LeaderboardController leaderboardController;

    public LeaderboardView(LeaderboardViewModel leaderboardViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
        this.leaderboardViewModel.addPropertyChangeListener(this);
        this.setBackground(new Color(219, 230, 234));

        LeaderboardState leaderboardState = leaderboardViewModel.getState();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(219, 255, 240));

        JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setFont(new Font("Agency FB",Font.PLAIN, 40));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(title);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        leaderboardPanel.setBackground(new Color(219, 229, 232));

        leaderboardPanel.add(createUserPanel(1,
                leaderboardState.getFirstPlaceName(),
                leaderboardState.getFirstPlacePoints()));
        leaderboardPanel.add(createUserPanel(2,
                leaderboardState.getSecondPlaceName(),
                leaderboardState.getSecondPlacePoints()));
        leaderboardPanel.add(createUserPanel(3,
                leaderboardState.getThirdPlaceName(),
                leaderboardState.getThirdPlacePoints()));


        leaderboardPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some spacing
        if (leaderboardState.getCurrentUserPlace() != null) {
            leaderboardPanel.add(createUserPanel(Integer.valueOf(
                    leaderboardState.getCurrentUserPlace()),
                    LeaderboardViewModel.YOU_LABEL,
                    leaderboardState.getCurrentUserPoints()));
        }


        mainPanel.add(leaderboardPanel);
        mainPanel.setBackground(new Color(219, 229, 232));

        menu = new JButton(LeaderboardViewModel.BACK_TO_MENU_BUTTON);
        menu.setFont(new Font("Arial", Font.PLAIN, 16));
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.setBackground(new Color(219, 229, 232));
        menu.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        leaderboardController.switchToMenuView();
                    }
                }
        );
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(menu);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBackground(new Color(219, 229, 232));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);
        this.setVisible(true);

    }

    private JPanel createUserPanel(Integer rank, String name, int avgPoints) {
        Color darkBlue = new Color(86, 135, 130);
        Color lightYellow = new Color(252,252, 197);
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());
        userPanel.setPreferredSize(new Dimension(350, 80));
        userPanel.setMaximumSize(new Dimension(350, 80)); // Fixed size
        userPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        userPanel.setBackground(rank == 1 ? lightYellow: rank <= 3 ? darkBlue : Color.WHITE);

        JLabel rankLabel = new JLabel(String.valueOf(rank));
        rankLabel.setFont(new Font("Arial", Font.BOLD, 36));
        rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rankLabel.setPreferredSize(new Dimension(50, 50));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel pointsLabel = new JLabel(String.valueOf(avgPoints));
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel userDetails = new JPanel(new BorderLayout());
        userDetails.add(nameLabel, BorderLayout.WEST);
        userDetails.add(pointsLabel, BorderLayout.EAST);
        userDetails.setPreferredSize(new Dimension(300, 50));
        userDetails.setBackground(new Color(219, 229, 232));

        userPanel.add(rankLabel, BorderLayout.WEST);
        userPanel.add(userDetails, BorderLayout.CENTER);
        userPanel.add(pointsLabel, BorderLayout.EAST);
        return userPanel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("Leaderboard State Update".equals(evt.getPropertyName())) {
            LeaderboardState newState = (LeaderboardState) evt.getNewValue();
            updateView(newState);
        }
    }

    private void updateView(LeaderboardState state) {
        // Update the leaderboard panel with new state
        removeAll();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(219, 229, 232));

        JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setFont(new Font("Agency FB",Font.PLAIN, 40));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBackground(new Color(219, 229, 232));
        mainPanel.add(title);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));

        leaderboardPanel.add(createUserPanel(1, state.getFirstPlaceName(), state.getFirstPlacePoints()));
        leaderboardPanel.add(createUserPanel(2, state.getSecondPlaceName(), state.getSecondPlacePoints()));
        leaderboardPanel.add(createUserPanel(3, state.getThirdPlaceName(), state.getThirdPlacePoints()));

        leaderboardPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        if (state.getCurrentUserPlace() != null) {
            leaderboardPanel.add(createUserPanel(Integer.valueOf(state.getCurrentUserPlace()), LeaderboardViewModel.YOU_LABEL, state.getCurrentUserPoints()));
        }
        leaderboardPanel.setBackground(new Color(219, 229, 232));
        mainPanel.add(leaderboardPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(menu);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
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

    public void setLeaderboardController(LeaderboardController leaderboardController) {
        this.leaderboardController = leaderboardController;
        leaderboardController.execute(leaderboardViewModel.getState().getCurrentUsername());
    }

}

