package Controllers;
import Animation.ShakeField;
import ClientSources.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

import static Animation.ShakeField.errorStyle;

public class RegistrController {
    private Client client = new Client();

    @FXML
    private Button exit_button;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField login_field;

    @FXML
    private Button login_button;

    @FXML
    private Label registLabel;

    //Открыть новое окно
    public void openWindow(String filename) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/" + filename + ".fxml"));
            loader.load();

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(filename + " view");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Регистрация
    public void signUpNewUser() {
        try {
            String loginText = login_field.getText().toLowerCase().trim();
            String passwordText = password_field.getText().toLowerCase().trim();
            Client client = new Client();

            if (!loginText.equals("") && (!passwordText.equals("")) && (client.registration(loginText, passwordText))) {
                login_button.getScene().getWindow().hide();
                openWindow("authorization");
            } else {
                ShakeField loginAnimation = new ShakeField(login_field);
                ShakeField passwordAnimation = new ShakeField(password_field);

                loginAnimation.playAnimation();
                passwordAnimation.playAnimation();

                login_field.clear();
                password_field.clear();
                login_field.setStyle(errorStyle);
                password_field.setStyle(errorStyle);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        registLabel.setText(client.getProperties().getProperty("Label_1"));
        exit_button.setText(client.getProperties().getProperty("Button_2"));
        login_button.setText(client.getProperties().getProperty("Button_1"));
        login_field.setPromptText(client.getProperties().getProperty("Field_1"));
        password_field.setPromptText(client.getProperties().getProperty("Field_2"));

        login_button.setOnAction(event -> {
            signUpNewUser();
        });

        exit_button.setOnAction(event -> {
            exit_button.getScene().getWindow().hide();
            openWindow("authorization");
        });

        login_button.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
                login_button.fire();
        });
        login_button.setDefaultButton(true);
    }
}
