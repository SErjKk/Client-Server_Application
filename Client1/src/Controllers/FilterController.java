package Controllers;
import Collection.FuelType;
import Collection.VehicleType;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ClientSources.Client;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;

import static Animation.ShakeField.errorStyle;
import static Animation.ShakeField.normalStyle;
import static Controllers.AuthorizationController.loginText;
import static Controllers.AuthorizationController.passwordText;

public class FilterController {

    ObservableList<String> fields = FXCollections.observableArrayList("id", "coord_x", "coord_y", "enginePower", "capacity", "type", "fuelType", "owner");
    ObservableList<String> conditions = FXCollections.observableArrayList(">", "<", "=", ">=", "<=");
    public static String res;
    public boolean a;
    private Client client = new Client();

    @FXML
    private ComboBox<String> conditionBox;

    @FXML
    private ComboBox<String> fieldBox;

    @FXML
    private TextField field;

    @FXML
    private Button filter_button;

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

    //Получить текст и условие фильтрации
    public void getText() {
        String cond = conditionBox.getValue();
        String f = field.getText();

        switch (fieldBox.getValue()) {
            case "id":
                if (Integer.parseInt(f) > 0) {
                    try {
                        Client client = new Client();
                        res = client.filter_id(loginText, passwordText, cond, Integer.parseInt(f));
                        if (res.equals("")) {
                            a = false;
                            filter_button.getScene().getWindow().hide();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText("No elements!");
                            alert.setResizable(false);
                            alert.showAndWait();
                            openWindow("home");
                        } else {
                            a = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    field.setStyle(errorStyle);
                    field.clear();
                    a = false;
                }
                break;
            case "coord_x":
                if (Long.parseLong(f) > 0) {
                    try {
                        Client client = new Client();
                        res = client.filter_x(loginText, passwordText, cond, Long.parseLong(f));
                        if (res.equals("")) {
                            a = false;
                            filter_button.getScene().getWindow().hide();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText("No elements!");
                            alert.setResizable(false);
                            alert.showAndWait();
                            openWindow("home");
                        } else {
                            a = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    field.setStyle(errorStyle);
                    field.clear();
                    a = false;
                }
                break;
            case "coord_y":
                if (Float.parseFloat(f) > 0) {
                    try {
                        Client client = new Client();
                        res = client.filter_y(loginText, passwordText, cond, Float.parseFloat(f));
                        if (res.equals("")) {
                            a = false;
                            filter_button.getScene().getWindow().hide();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText("No elements!");
                            alert.setResizable(false);
                            alert.showAndWait();
                            openWindow("home");
                        } else {
                            a = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    field.setStyle(errorStyle);
                    field.clear();
                    a = false;
                }
                break;
            case "enginePower":
                if (Long.parseLong(f) > 0) {
                    try {
                        Client client = new Client();
                        res = client.filter_power(loginText, passwordText, cond, Long.parseLong(f));
                        if (res.equals("")) {
                            a = false;
                            filter_button.getScene().getWindow().hide();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText("No elements!");
                            alert.setResizable(false);
                            alert.showAndWait();
                            openWindow("home");
                        } else {
                            a = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    field.setStyle(errorStyle);
                    field.clear();
                    a = false;
                }
                break;
            case "capacity":
                if (Double.parseDouble(f) > 0) {
                    try {
                        Client client = new Client();
                        res = client.filter_capacity(loginText, passwordText, cond, Double.parseDouble(f));
                        if (res.equals("")) {
                            a = false;
                            filter_button.getScene().getWindow().hide();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText("No elements!");
                            alert.setResizable(false);
                            alert.showAndWait();
                            openWindow("home");
                        } else {
                            a = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    field.setStyle(errorStyle);
                    field.clear();
                    a = false;
                }
                break;
            case "type":
                String type = f.toUpperCase().trim();
                if (((type.equals("CHOPPER")) || (type.equals("SUBMARINE")) || (type.equals("SPACESHIP"))
                        || (type.equals("NULL"))) && (conditionBox.getValue().equals("="))) {
                    try {
                        Client client = new Client();
                        res = client.filter_type(loginText, passwordText, VehicleType.valueOf(type));
                        if (res.equals("")) {
                            a = false;
                            filter_button.getScene().getWindow().hide();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText("No elements!");
                            alert.setResizable(false);
                            alert.showAndWait();
                            openWindow("home");
                        } else {
                            a = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    field.setStyle(errorStyle);
                    conditionBox.setStyle(errorStyle);
                    field.clear();
                    a = false;
                }
                break;
            case "fuelType":
                String fuel = f.toUpperCase().trim();
                if (((fuel.equals("ALCOHOL")) || (fuel.equals("KEROSENE")) || (fuel.equals("NULL"))
                        || (fuel.equals("GASOLINE")) || (fuel.equals("ANTIMATTER"))) && (conditionBox.getValue().equals("="))) {
                    try {
                        Client client = new Client();
                        res = client.filter_fuel(loginText, passwordText, FuelType.valueOf(fuel
                        ));
                        if (res.equals("")) {
                            a = false;
                            filter_button.getScene().getWindow().hide();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText("No elements!");
                            alert.setResizable(false);
                            alert.showAndWait();
                            openWindow("home");
                        } else {
                            a = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    field.setStyle(errorStyle);
                    conditionBox.setStyle(errorStyle);
                    field.clear();
                    a = false;
                }
                break;
            case "owner":
                String owner = f.toLowerCase().trim();
                if (conditionBox.getValue().equals("=")) {
                    try {
                        Client client = new Client();
                        res = client.filter_owner(loginText, passwordText, owner);
                        if (res.equals("")) {
                            a = false;
                            filter_button.getScene().getWindow().hide();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText("No elements!");
                            alert.setResizable(false);
                            alert.showAndWait();
                            openWindow("home");
                        } else {
                            a = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    field.setStyle(errorStyle);
                    conditionBox.setStyle(errorStyle);
                    field.clear();
                    a = false;
                }
                break;
        }
    }

    @FXML
    void initialize() {
        filter_button.setText(client.getProperties().getProperty("Button_17"));

        conditionBox.setItems(conditions);
        fieldBox.setItems(fields);

        filter_button.setOnAction(event -> {
            getText();
            if (a) {
                filter_button.getScene().getWindow().hide();
                openWindow("filter table");
            }
        });

        filter_button.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
                filter_button.fire();
        });
        filter_button.setDefaultButton(true);
    }
}