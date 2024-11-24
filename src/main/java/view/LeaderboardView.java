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

        JLabel title = new JLabel("Leaderboard");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title, BorderLayout.NORTH);

        JPanel leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        leaderboardPanel.setBackground(Color.WHITE);

        // populate with top users
        List<LeaderboardViewModel.UserData> users = leaderboardViewModel.getUsers();
        leaderboardPanel.removeAll();

        //display top users
        for (int i = 0; i < Math.min(3, users.size()); i++) {
            LeaderboardViewModel.UserData user = users.get(i);
            leaderboardPanel.add(createUserPanel(i + 1, user.getName(), Math.floor(user.getAveragePoints())));
        }

        JScrollPane scrollPane = new JScrollPane(leaderboardPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        menu = new JButton(leaderboardViewModel.BACK_TO_MENU_BUTTON);
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        leaderboardController.switchToMenuView();
                    }
                }
        );

        buttonsPanel.add(menu);

    }

    private JPanel createUserPanel(int rank, String name, double avgPoints) {
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

        JLabel pointsLabel = new JLabel(String.valueOf((int) avgPoints));
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        pointsLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel userDetails = new JPanel(new BorderLayout());
        userDetails.add(nameLabel, BorderLayout.WEST);
        userDetails.add(pointsLabel, BorderLayout.EAST);
        userDetails.setPreferredSize(new Dimension(300, 50));

        userPanel.add(rankLabel, BorderLayout.WEST);
        userPanel.add(userDetails, BorderLayout.CENTER);

        return userPanel;
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public String getViewName() {return viewName;}

    public void setLeaderboardController(LeaderboardController leaderboardController) { this.leaderboardController = leaderboardController;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

