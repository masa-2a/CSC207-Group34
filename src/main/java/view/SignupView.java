package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

/**
 * The View for the Signup Use Case.
 */
public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final String VIEW_NAME = "sign up";
    private static final int INPUT_FIELD_WIDTH = 15;
    private static final int HORIZONTAL_STRUT_SIZE = 15;
    private static final int GREETING_FONT_SIZE = 40;
    private static final String FONT_NAME = "Agency FB";
    private static final int BACKGROUND_COLOR_R = 219;
    private static final int BACKGROUND_COLOR_G = 229;
    private static final int BACKGROUND_COLOR_B = 232;

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(INPUT_FIELD_WIDTH);
    private final JPasswordField passwordInputField = new JPasswordField(INPUT_FIELD_WIDTH);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(INPUT_FIELD_WIDTH);
    private SignupController signupController;

    private final JButton signUp;
    private final JButton toLogin;

    public SignupView(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);
        this.setBackground(new Color(BACKGROUND_COLOR_R, BACKGROUND_COLOR_G, BACKGROUND_COLOR_B));

        final JLabel greeting = new JLabel("Welcome to Map Master!");
        final JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        greeting.setAlignmentX(Component.CENTER_ALIGNMENT);
        greeting.setFont(new Font(FONT_NAME, Font.PLAIN, GREETING_FONT_SIZE));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        usernameInfo.setBackground(new Color(BACKGROUND_COLOR_R, BACKGROUND_COLOR_G, BACKGROUND_COLOR_B));

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        passwordInfo.setBackground(new Color(BACKGROUND_COLOR_R, BACKGROUND_COLOR_G, BACKGROUND_COLOR_B));

        final LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        repeatPasswordInfo.setBackground(new Color(BACKGROUND_COLOR_R, BACKGROUND_COLOR_G, BACKGROUND_COLOR_B));

        final JPanel buttons = new JPanel();
        toLogin = new JButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);
        buttons.add(toLogin);
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        buttons.setBackground(new Color(BACKGROUND_COLOR_R, BACKGROUND_COLOR_G, BACKGROUND_COLOR_B));

        signUp.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            final SignupState currentState = signupViewModel.getState();

                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword()
                            );
                        }
                    }
                }
        );

        toLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        signupController.switchToLoginView();
                    }
                }
        );

        addUsernameListener();
        addPasswordListener();
        addRepeatPasswordListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(greeting);
        this.add(title);
        this.add(Box.createHorizontalStrut(HORIZONTAL_STRUT_SIZE));
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
    }

    private void addUsernameListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                signupViewModel.setState(currentState);
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
    }

    private void addPasswordListener() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                signupViewModel.setState(currentState);
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
    }

    private void addRepeatPasswordListener() {
        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordInputField.getPassword()));
                signupViewModel.setState(currentState);
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
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public String getViewName() {
        return VIEW_NAME;
    }

    public void setSignupController(SignupController controller) {
        this.signupController = controller;
    }
}
