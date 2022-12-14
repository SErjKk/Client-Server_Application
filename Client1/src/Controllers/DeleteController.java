package Controllers;
import ClientSources.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import java.io.IOException;
import static Animation.ShakeField.errorStyle;
import static Animation.ShakeField.normalStyle;
import static Controllers.AuthorizationController.loginText;
import static Controllers.AuthorizationController.passwordText;

public class DeleteController {

    private int field;
    boolean a = true;
    private boolean cond;
    private Client client = new Client();

    @FXML
    private TextField idField;

    @FXML
    private Button delete_button;

    @FXML
    private Label idLabel;

    //Пытаемся отпределить тип ввода
    Integer tryParseInt(String s) {
        try {
            return new Integer(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    //Проверка удаления
    public boolean checkDeleted(int id) throws IOException {
        Client client = new Client();
        return client.delete(loginText, passwordText, id);
    }

    //Получаем значение поля
    public void getText() {
        String id = idField.getText();
        Integer tryId = tryParseInt(id);

        if ((id.equals("")) || (Integer.parseInt(id) <= 0) || (tryId == null)) {
            idField.setStyle(errorStyle);
            idField.clear();
            a = false;
        } else {
            idField.setStyle(normalStyle);
        }

        if (a) {
            try {
                field = Integer.parseInt(id);
                cond = checkDeleted(field);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        idLabel.setText(client.getProperties().getProperty("Label_33"));
        delete_button.setText(client.getProperties().getProperty("Button_16"));

        delete_button.setOnAction(event -> {
            getText();
            if (cond) {
                delete_button.getScene().getWindow().hide();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("You can only delete your own objects!");
                alert.setResizable(false);
                alert.showAndWait();
            }
        });

        delete_button.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
                delete_button.fire();
        });
        delete_button.setDefaultButton(true);
    }
}