package Controllers;
import ClientSources.Client;
import Collection.FuelType;
import Collection.Vehicle;
import Collection.VehicleType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

import static Animation.ShakeField.errorStyle;
import static Animation.ShakeField.normalStyle;
import static Controllers.AuthorizationController.loginText;
import static Controllers.AuthorizationController.passwordText;

public class IdController {

    public static int field;
    boolean a = true;
    private boolean cond;
    private Client client = new Client();

    @FXML
    private TextField idField;

    @FXML
    private Button update_button;

    @FXML
    private Label idLabel;

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

    //Пытаемся отпределить тип ввода
    Integer tryParseInt(String s) {
        try {
            return new Integer(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    //Проверка изменения
    public boolean checkUpdated(Vehicle vehicle) throws IOException {
        Client client = new Client();
        return client.update(loginText, passwordText, vehicle);
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
                Vehicle vehicle = new Vehicle(field, "1", 5L, 5, LocalDateTime.now(), 5, 5, VehicleType.CHOPPER, FuelType.ALCOHOL, loginText);
                cond = checkUpdated(vehicle);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        idLabel.setText(client.getProperties().getProperty("Label_32"));
        update_button.setText(client.getProperties().getProperty("Button_15"));

        update_button.setOnAction(event -> {
            getText();
            if (cond) {
                update_button.getScene().getWindow().hide();
                openWindow("update");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("You can only update your own objects!");
                alert.setResizable(false);
                alert.showAndWait();
            }
        });

        update_button.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
                update_button.fire();
        });
        update_button.setDefaultButton(true);
    }
}