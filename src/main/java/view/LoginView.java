package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

/**
 * The View for when the user is logging into the program.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    private static final String VIEW_NAME = "log in";
    private static final int TITLE_FONT_SIZE = 40;
    private static final int LOGO_WIDTH = 200;
    private static final int LOGO_HEIGHT = 100;
    private static final int TEXT_FIELD_COLUMNS = 15;
    private static final int BOX_HORIZONTAL_STRUT = 1;

    private static final Color BACKGROUND_COLOR = new Color(219, 229, 232);

    private final LoginViewModel loginViewModel;

    private final JTextField usernameInputField = new JTextField(TEXT_FIELD_COLUMNS);
    private final JLabel usernameErrorField = new JLabel();
    private final JPasswordField passwordInputField = new JPasswordField(TEXT_FIELD_COLUMNS);
    private final JLabel passwordErrorField = new JLabel();

    private final JButton logIn;
    private final JButton signUp;
    private LoginController loginController;

    public LoginView(LoginViewModel loginViewModel) {

        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);
        this.setBackground(BACKGROUND_COLOR);

        final JLabel title = new JLabel("Login");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Agency FB", Font.PLAIN, TITLE_FONT_SIZE));

        final ImageIcon logo = new ImageIcon("src/main/resources/MapMasterName.png");
        final Image image = logo.getImage();
        final Image newimg = image.getScaledInstance(LOGO_WIDTH, LOGO_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        final ImageIcon logoScaled = new ImageIcon(newimg);

        final JLabel imageLabel = new JLabel(logoScaled);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        usernameInfo.setBackground(BACKGROUND_COLOR);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);
        passwordInfo.setBackground(BACKGROUND_COLOR);

        final JPanel buttons = new JPanel();
        logIn = new JButton("Log in");
        buttons.add(logIn);
        signUp = new JButton("Go to Sign Up");
        buttons.add(signUp);
        buttons.setBackground(BACKGROUND_COLOR);

        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            final LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        signUp.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        loginController.switchToSignupView();
                    }

                }
        );

        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
        this.add(imageLabel);
        this.add(title);
        this.add(Box.createHorizontalStrut(BOX_HORIZONTAL_STRUT));
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        usernameErrorField.setText(state.getLoginError());
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
    }

    public String getViewName() {
        return VIEW_NAME;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
