package Controllers;
import ClientSources.Client;
import Collection.FuelType;
import Collection.Vehicle;
import Collection.VehicleType;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;

import static Controllers.AuthorizationController.loginText;
import static Controllers.FilterController.res;

public class FilterTableController {

    public static ObservableList<Vehicle> spisok;
    private Client client = new Client();

    @FXML
    private Button exit_button;

    @FXML
    private Button table_button;

    @FXML
    private Label ownerLabel;

    @FXML
    private TableView<Vehicle> filter_table;
    @FXML
    private TableColumn<Vehicle, Integer> id_field;
    @FXML
    private TableColumn<Vehicle, String> name_field;
    @FXML
    private TableColumn<Vehicle, Long> x_field;
    @FXML
    private TableColumn<Vehicle, Float> y_field;
    @FXML
    private TableColumn<Vehicle, LocalDateTime> date_field;
    @FXML
    private TableColumn<Vehicle, Long> power_field;
    @FXML
    private TableColumn<Vehicle, Double> capacity_field;
    @FXML
    private TableColumn<Vehicle, VehicleType> vehicleType_field;
    @FXML
    private TableColumn<Vehicle, FuelType> fuelType_field;
    @FXML
    private TableColumn<Vehicle, String> owner_field;

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

    //Инициализация таблицы объектов
    public void initTable() {
        String[] arr = res.split("   ");
        spisok = FXCollections.observableArrayList();

        int n = arr.length / 10;
        for (int i = ((arr.length / n) - 1); i < arr.length; i += 10) {
            Vehicle vehicle = new Vehicle(Integer.parseInt(arr[i - 9]), arr[i - 8],
                    Long.parseLong(arr[i - 7]), Float.parseFloat(arr[i - 6]), LocalDateTime.now(),
                    Long.parseLong(arr[i - 4]), Double.parseDouble(arr[i - 3]),
                    VehicleType.valueOf(arr[i - 2].toUpperCase()), FuelType.valueOf(arr[i - 1].toUpperCase()), arr[i]);
            spisok.add(vehicle);
        }

        id_field.setCellValueFactory(new PropertyValueFactory<>("id"));
        id_field.setResizable(false);
        name_field.setCellValueFactory(new PropertyValueFactory<>("name"));
        name_field.setResizable(false);
        x_field.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getCoordinates().getX()).asObject());
        x_field.setResizable(false);
        y_field.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getCoordinates().getY()).asObject());
        y_field.setResizable(false);
        date_field.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        date_field.setResizable(false);
        power_field.setCellValueFactory(new PropertyValueFactory<>("enginePower"));
        power_field.setResizable(false);
        capacity_field.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        capacity_field.setResizable(false);
        vehicleType_field.setCellValueFactory(new PropertyValueFactory<>("type"));
        vehicleType_field.setResizable(false);
        fuelType_field.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        fuelType_field.setResizable(false);
        owner_field.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOwner()));
        owner_field.setResizable(false);

        filter_table.setItems(spisok);
    }

    @FXML
    void initialize() {
        table_button.setText(client.getProperties().getProperty("Button_18"));
        exit_button.setText(client.getProperties().getProperty("Button_19"));
        ownerLabel.setText(client.getProperties().getProperty("Label_34"));
        ownerLabel.setText(ownerLabel.getText() + loginText);

        initTable();

        exit_button.setOnAction(event -> {
            exit_button.getScene().getWindow().hide();
            openWindow("authorization");
        });

        table_button.setOnAction(event -> {
            table_button.getScene().getWindow().hide();
            openWindow("home");
        });
    }
}