package Controllers;
import ClientSources.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import static Controllers.AuthorizationController.loginText;
import static Controllers.AuthorizationController.passwordText;
import static Controllers.ShowController.vehicle;

public class InfoController {
    private Client client = new Client();

    @FXML
    private Button delete_button;

    @FXML
    private Button change_button;

    @FXML
    private Label coordLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label yLabel;

    @FXML
    private Label xLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label powerLabel;

    @FXML
    private Label capacityLabel;

    @FXML
    private Label fuelLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label ownerLabel;

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

    //Подписи полей объекта
    public void setText() {
        idLabel.setText(idLabel.getText() + "[id = " + vehicle.getId() + "]");
        nameLabel.setText(nameLabel.getText() + vehicle.getName());
        xLabel.setText(xLabel.getText() + vehicle.getCoordinates().getX());
        yLabel.setText(yLabel.getText() + vehicle.getCoordinates().getY());
        dateLabel.setText(dateLabel.getText() + vehicle.getCreationDate());
        powerLabel.setText(powerLabel.getText() + vehicle.getEnginePower());
        capacityLabel.setText(capacityLabel.getText() + vehicle.getCapacity());
        typeLabel.setText(typeLabel.getText() + vehicle.getType());
        fuelLabel.setText(fuelLabel.getText() + vehicle.getFuelType());
        ownerLabel.setText(ownerLabel.getText() + vehicle.getOwner());
    }

    //Проверка удаления
    public boolean checkDeleted() throws IOException {
        Client client = new Client();
        return client.delete(loginText, passwordText, vehicle.getId());
    }

    @FXML
    void initialize() {
        idLabel.setText(client.getProperties().getProperty("Label_5"));
        nameLabel.setText(client.getProperties().getProperty("Label_6"));
        coordLabel.setText(client.getProperties().getProperty("Label_7"));
        xLabel.setText(client.getProperties().getProperty("Label_8"));
        yLabel.setText(client.getProperties().getProperty("Label_9"));
        dateLabel.setText(client.getProperties().getProperty("Label_10"));
        powerLabel.setText(client.getProperties().getProperty("Label_11"));
        capacityLabel.setText(client.getProperties().getProperty("Label_12"));
        typeLabel.setText(client.getProperties().getProperty("Label_13"));
        fuelLabel.setText(client.getProperties().getProperty("Label_14"));
        ownerLabel.setText(client.getProperties().getProperty("Label_15"));
        delete_button.setText(client.getProperties().getProperty("Button_11"));
        change_button.setText(client.getProperties().getProperty("Button_12"));

        setText();

        delete_button.setOnAction(event -> {
            try {
                if (checkDeleted()) {
                    delete_button.getScene().getWindow().hide();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("You can only delete your own objects!");
                    alert.setResizable(false);
                    alert.showAndWait();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        change_button.setOnAction(event -> {
            if (vehicle.getOwner().equals(loginText)) {
                change_button.getScene().getWindow().hide();
                openWindow("update");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("You can only update your own objects!");
                alert.setResizable(false);
                alert.showAndWait();
            }
        });
    }
}