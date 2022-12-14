package Controllers;
import ClientSources.Client;
import Collection.FuelType;
import Collection.Vehicle;
import Collection.VehicleType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import java.io.IOException;
import java.time.LocalDateTime;
import static Animation.ShakeField.errorStyle;
import static Animation.ShakeField.normalStyle;
import static Controllers.AuthorizationController.loginText;
import static Controllers.AuthorizationController.passwordText;
import static Controllers.ShowController.vehicle;
import static Controllers.IdController.field;

public class UpdateController {

    private boolean cond;
    private Client client = new Client();

    @FXML
    private TextField xField;

    @FXML
    private TextField powerField;

    @FXML
    private Button update_button;

    @FXML
    private TextField yField;

    @FXML
    private TextField capacityField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField fuelField;

    @FXML
    private TextField typeField;

    @FXML
    private Label coordLabel;

    @FXML
    private Label xLabel;

    @FXML
    private Label yLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label powerLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label fuelLabel;

    @FXML
    private Label capacityLabel;

    //Пытаемся определить тип ввода
    Double tryParseDouble(String s) {
        try {
            return new Double(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    Long tryParseLong(String s) {
        try {
            return new Long(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    Float tryParseFloat(String s) {
        try {
            return new Float(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    VehicleType tryParseType(String s) {
        try {
            return VehicleType.valueOf(s);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    FuelType tryParseFuelType(String s) {
        try {
            return FuelType.valueOf(s);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    //Проверка изменения
    public boolean checkUpdated(Vehicle vehicle) throws IOException {
        Client client = new Client();
        return client.update(loginText, passwordText, vehicle);
    }

    //Получить новые поля объекта
    public void getText() {
        int id;
        LocalDateTime date;
        String owner;
        try {
            try {
                id = vehicle.getId();
            } catch (NullPointerException e) {
                id = field;
            }
            String name = nameField.getText().toLowerCase();
            String x = xField.getText();
            String y = yField.getText();
            try {
                date = vehicle.getCreationDate();
            } catch (NullPointerException e) {
                date = LocalDateTime.now();
            }
            String enginePower = powerField.getText();
            String capacity = capacityField.getText();
            String type = typeField.getText().toUpperCase();
            String fuelType = fuelField.getText().toUpperCase();
            try {
                owner = vehicle.getOwner();
            } catch (NullPointerException e) {
                owner = loginText;
            }

            Long tryX = tryParseLong(x);
            Float tryY = tryParseFloat(y);
            Long tryPower = tryParseLong(enginePower);
            Double tryCapacity = tryParseDouble(capacity);
            VehicleType tryType = tryParseType(type);
            FuelType tryFuel = tryParseFuelType(fuelType);

            boolean a = true, b = true, c = true, d = true, e = true, f = true, g = true;
            if (name.equals("")) {
                nameField.setStyle(errorStyle);
                a = false;
            } else {
                nameField.setStyle(normalStyle);
            }
            if ((x.equals("")) || (Long.parseLong(x) <= 0) || (tryX == null)) {
                xField.setStyle(errorStyle);
                xField.clear();
                b = false;
            } else {
                xField.setStyle(normalStyle);
            }
            if ((y.equals("")) || (Float.parseFloat(y) <= 0) || (tryY == null)) {
                yField.setStyle(errorStyle);
                yField.clear();
                c = false;
            } else {
                yField.setStyle(normalStyle);
            }
            if ((enginePower.equals("")) || (Long.parseLong(enginePower) <= 0) || (tryPower == null)) {
                powerField.setStyle(errorStyle);
                powerField.clear();
                d = false;
            } else {
                powerField.setStyle(normalStyle);
            }
            if ((capacity.equals("")) || (Double.parseDouble(capacity) <= 0) || (tryCapacity == null)) {
                capacityField.setStyle(errorStyle);
                capacityField.clear();
                e = false;
            } else {
                capacityField.setStyle(normalStyle);
            }
            if ((type.equals("")) || (tryType == null)) {
                typeField.setStyle(errorStyle);
                typeField.clear();
                f = false;
            } else {
                typeField.setStyle(normalStyle);
            }
            if ((fuelType.equals("")) || (tryFuel == null)) {
                fuelField.setStyle(errorStyle);
                fuelField.clear();
                g = false;
            } else {
                fuelField.setStyle(normalStyle);
            }
            if (a && b && c && d && e && f && g) {
                update_button.getScene().getWindow().hide();
                Vehicle vehicle = new Vehicle(id, name, Long.parseLong(x), Float.parseFloat(y), date, Long.parseLong(enginePower), Double.parseDouble(capacity), VehicleType.valueOf(type), FuelType.valueOf(fuelType), owner);
                cond = checkUpdated(vehicle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        nameLabel.setText(client.getProperties().getProperty("Label_24"));
        coordLabel.setText(client.getProperties().getProperty("Label_25"));
        xLabel.setText(client.getProperties().getProperty("Label_26"));
        yLabel.setText(client.getProperties().getProperty("Label_27"));
        powerLabel.setText(client.getProperties().getProperty("Label_28"));
        capacityLabel.setText(client.getProperties().getProperty("Label_29"));
        typeLabel.setText(client.getProperties().getProperty("Label_30"));
        fuelLabel.setText(client.getProperties().getProperty("Label_31"));
        update_button.setText(client.getProperties().getProperty("Button_14"));

        update_button.setOnAction(event -> {
            getText();
            if (cond) {
                update_button.getScene().getWindow().hide();
            }
        });

        update_button.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
                update_button.fire();
        });
        update_button.setDefaultButton(true);
    }
}