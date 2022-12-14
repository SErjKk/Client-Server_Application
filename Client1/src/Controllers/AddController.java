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

public class AddController {

    private boolean cond;
    private Client client = new Client();

    @FXML
    private Button add_button;

    @FXML
    private TextField xField;

    @FXML
    private TextField powerField;

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
    public boolean checkAdded(Vehicle vehicle) throws IOException {
        Client client = new Client();
        return client.add(loginText, passwordText, vehicle);
    }

    //Получить новые поля объекта
    public void getText() {
        try {
            int id = 1;
            String name = nameField.getText().toLowerCase();
            String x = xField.getText();
            String y = yField.getText();
            LocalDateTime date = LocalDateTime.now();
            String enginePower = powerField.getText();
            String capacity = capacityField.getText();
            String type = typeField.getText().toUpperCase();
            String fuelType = fuelField.getText().toUpperCase();
            String owner = loginText;

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
                add_button.getScene().getWindow().hide();
                Vehicle vehicle = new Vehicle(id, name, Long.parseLong(x), Float.parseFloat(y), date, Long.parseLong(enginePower), Double.parseDouble(capacity), VehicleType.valueOf(type), FuelType.valueOf(fuelType), owner);
                cond = checkAdded(vehicle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        nameLabel.setText(client.getProperties().getProperty("Label_16"));
        coordLabel.setText(client.getProperties().getProperty("Label_17"));
        xLabel.setText(client.getProperties().getProperty("Label_18"));
        yLabel.setText(client.getProperties().getProperty("Label_19"));
        powerLabel.setText(client.getProperties().getProperty("Label_20"));
        capacityLabel.setText(client.getProperties().getProperty("Label_21"));
        typeLabel.setText(client.getProperties().getProperty("Label_22"));
        fuelLabel.setText(client.getProperties().getProperty("Label_23"));
        add_button.setText(client.getProperties().getProperty("Button_13"));

        add_button.setOnAction(event -> {
            getText();
            if (cond) {
                add_button.getScene().getWindow().hide();
            }
        });

        add_button.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
                add_button.fire();
        });
        add_button.setDefaultButton(true);
    }
}