package view;

import interface_adapter.leaderboard.LeaderboardController;
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
        this.leaderboardViewModel = new LeaderboardViewModel();
        leaderboardViewModel.addPropertyChangeListener(this);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        //this.add(title, BorderLayout.NORTH);
        mainPanel.add(title);

        //this.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        //leaderboardPanel.setBackground(Color.WHITE);

        // populate with top users
        //leaderboardPanel.removeAll();

        //display top users

        leaderboardPanel.add(createUserPanel(1, LeaderboardViewModel.getFirstPlaceName(), LeaderboardViewModel.getFirstPlacePoints()));
        leaderboardPanel.add(createUserPanel(2, LeaderboardViewModel.getSecondPlaceName(), LeaderboardViewModel.getSecondPlacePoints()));
        leaderboardPanel.add(createUserPanel(3, LeaderboardViewModel.getThirdPlaceName(), LeaderboardViewModel.getThirdPlacePoints()));


        leaderboardPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some spacing
        if (LeaderboardViewModel.getCurrentUserPlace() != null) {
            leaderboardPanel.add(createUserPanel(Integer.valueOf(LeaderboardViewModel.getCurrentUserPlace()), LeaderboardViewModel.YOU_LABEL, LeaderboardViewModel.getCurrentUserPoints()));
        }

        mainPanel.add(leaderboardPanel);

        menu = new JButton(LeaderboardViewModel.BACK_TO_MENU_BUTTON);
        menu.setFont(new Font("Arial", Font.PLAIN, 16));
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);
        this.setVisible(true);

    }

    private JPanel createUserPanel(Integer rank, String name, String avgPoints) {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());
        userPanel.setPreferredSize(new Dimension(350, 80));
        userPanel.setMaximumSize(new Dimension(350, 80)); // Fixed size
        userPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        userPanel.setBackground(rank == 1 ? Color.YELLOW : rank <= 3 ? Color.LIGHT_GRAY : Color.WHITE);

        JLabel rankLabel = new JLabel(String.valueOf(rank));
        rankLabel.setFont(new Font("Arial", Font.BOLD, 36));
        rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rankLabel.setPreferredSize(new Dimension(50, 50));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel pointsLabel = new JLabel(avgPoints);
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel userDetails = new JPanel(new BorderLayout());
        userDetails.add(nameLabel, BorderLayout.WEST);
        userDetails.add(pointsLabel, BorderLayout.EAST);
        userDetails.setPreferredSize(new Dimension(300, 50));

        userPanel.add(rankLabel, BorderLayout.WEST);
        userPanel.add(userDetails, BorderLayout.CENTER);
        userPanel.add(pointsLabel, BorderLayout.EAST);

        return userPanel;
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
        leaderboardController.execute(LeaderboardViewModel.getCurrentUsername());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

