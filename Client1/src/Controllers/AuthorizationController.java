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

public class AuthorizationController {

    public static String loginText;
    public static String passwordText;
    private Client client = new Client();

    @FXML
    private PasswordField password_field;

    @FXML
    private Button enter_button;

    @FXML
    private TextField login_field;

    @FXML
    private Label authLabel;

    @FXML
    private Button login_button;

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

    //Проверка авторизации
    public void checkUser() {
        try {
            loginText = login_field.getText().toLowerCase().trim();
            passwordText = password_field.getText().toLowerCase().trim();
            Client client = new Client();

            if  (!loginText.equals("") && (!passwordText.equals("")) && (client.authorization(loginText, passwordText))) {
                enter_button.getScene().getWindow().hide();
                openWindow("home");
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
        authLabel.setText(client.getProperties().getProperty("Label_2"));
        enter_button.setText(client.getProperties().getProperty("Button_4"));
        login_button.setText(client.getProperties().getProperty("Button_3"));
        login_field.setPromptText(client.getProperties().getProperty("Field_3"));
        password_field.setPromptText(client.getProperties().getProperty("Field_4"));

        enter_button.setOnAction(event -> {
            checkUser();
        });

        login_button.setOnAction(event -> {
            login_button.getScene().getWindow().hide();
            openWindow("registration");
        });

        enter_button.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
                enter_button.fire();
        });
        enter_button.setDefaultButton(true);
    }
}