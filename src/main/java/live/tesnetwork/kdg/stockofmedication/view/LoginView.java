package live.tesnetwork.kdg.stockofmedication.view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import live.tesnetwork.kdg.stockofmedication.logic.LoginViewHandler;

public class LoginView extends VBox implements ViewHelper {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;

    public LoginView() {

    }

    @Override
    public void initialize() {
        // Set up VBox properties
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));

        // Initialize username field
        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();
        usernameField.setPromptText("Enter your username");

        // Initialize password field
        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        // Initialize login button
        loginButton = new Button("Login");
        loginButton.addEventHandler(ActionEvent.ACTION, event -> {
            LoginViewHandler.login(this);
        });

        // Initialize register button
        registerButton = new Button("Register");
        registerButton.addEventHandler(ActionEvent.ACTION, event -> {
            LoginViewHandler.register(this);
        });

        // Set up HBox for buttons
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(registerButton, loginButton);

        // Add components to the VBox
        getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, buttonBox);
    }

    @Override
    public String getTitle() {
        return "Login view";
    }

    // Getters for the username, password, login button, and register button
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }
}
