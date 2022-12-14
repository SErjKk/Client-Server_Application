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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;
import static Controllers.AuthorizationController.loginText;
import static Controllers.AuthorizationController.passwordText;

public class HomeController {

    public static ObservableList<Vehicle> list;
    private final MenuItem help = new MenuItem("HELP");
    private final MenuItem info = new MenuItem("INFO");
    private final MenuItem clear = new MenuItem("CLEAR");
    private final MenuItem add = new MenuItem("ADD");
    private final MenuItem delete = new MenuItem("DELETE");
    private final MenuItem average = new MenuItem("AVERAGE");
    private final MenuItem update = new MenuItem("UPDATE");

    private final Menu commands = new Menu("Commands");
    private final MenuItem filter = new MenuItem("Filters");
    private final ContextMenu menu = new ContextMenu();
    private Client client = new Client();

    @FXML
    private Button exit_button;

    @FXML
    private Label ownerLabel;

    @FXML
    private Button show_button;

    @FXML
    private Button restart_button;

    @FXML
    private TableView<Vehicle> objects_table;
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

    //Вернуть лист объектов
    public static ObservableList<Vehicle> getList() {
        try {
            Client client = new Client();
            String[] list1 = client.show(loginText, passwordText).split("   ");
            list = FXCollections.observableArrayList();

            int n = list1.length / 10;
            for (int i = ((list1.length / n) - 1); i < list1.length; i += 10) {
                Vehicle vehicle = new Vehicle(Integer.parseInt(list1[i - 9]), list1[i - 8],
                        Long.parseLong(list1[i - 7]), Float.parseFloat(list1[i - 6]), LocalDateTime.now(),
                        Long.parseLong(list1[i - 4]), Double.parseDouble(list1[i - 3]),
                        VehicleType.valueOf(list1[i - 2].toUpperCase()), FuelType.valueOf(list1[i - 1].toUpperCase()),
                        list1[i]);
                list.add(vehicle);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Инициализация таблицы объектов
    public void initTable() {
        try {
            Client client = new Client();
            String[] list1 = client.show(loginText, passwordText).split("   ");
            list = FXCollections.observableArrayList();

            int n = list1.length/10;
            for (int i = ((list1.length/n)-1); i < list1.length; i+=10) {
                Vehicle vehicle = new Vehicle(Integer.parseInt(list1[i-9]), list1[i-8],
                        Long.parseLong(list1[i-7]), Float.parseFloat(list1[i-6]),  LocalDateTime.now(),
                        Long.parseLong(list1[i-4]), Double.parseDouble(list1[i-3]),
                        VehicleType.valueOf(list1[i-2].toUpperCase()), FuelType.valueOf(list1[i-1].toUpperCase()),
                        list1[i]);
                list.add(vehicle);
            }

            id_field.setCellValueFactory(new PropertyValueFactory<>("id"));
            id_field.setResizable(false);

            name_field.setCellValueFactory(new PropertyValueFactory<>("name"));
            name_field.setCellFactory(TextFieldTableCell.forTableColumn());
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

            objects_table.setItems(list);
            objects_table.setEditable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        ownerLabel.setText(client.getProperties().getProperty("Label_3"));
        show_button.setText(client.getProperties().getProperty("Button_5"));
        restart_button.setText(client.getProperties().getProperty("Button_6"));
        exit_button.setText(client.getProperties().getProperty("Button_7"));

        initTable();

        commands.getItems().addAll(help, info, clear, add, delete, update, average);
        menu.getItems().addAll(commands, filter);
        objects_table.setContextMenu(menu);

        ownerLabel.setText(ownerLabel.getText() + loginText);

        exit_button.setOnAction(event -> {
            exit_button.getScene().getWindow().hide();
            openWindow("authorization");
        });

        show_button.setOnAction(event -> {
            show_button.getScene().getWindow().hide();
            openWindow("visual");
        });

        restart_button.setOnAction(event -> {
            initTable();
        });

        help.setOnAction(event -> {
            try {
                Client client = new Client();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("HELP");
                alert.setHeaderText(client.help(loginText, passwordText));
                alert.setResizable(false);
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        info.setOnAction(event -> {
            try {
                Client client = new Client();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFO");
                alert.setHeaderText(client.info(loginText, passwordText));
                alert.setResizable(false);
                alert.showAndWait();
                initTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        clear.setOnAction(event -> {
            try {
                Client client = new Client();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CLEAR");
                alert.setHeaderText(client.clear(loginText, passwordText));
                alert.setResizable(false);
                alert.showAndWait();
                initTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        average.setOnAction(event -> {
            try {
                Client client = new Client();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("AVERAGE");
                alert.setHeaderText(client.average(loginText, passwordText));
                alert.setResizable(false);
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        add.setOnAction(event -> {
            openWindow("add");
            initTable();
        });
        delete.setOnAction(event -> {
            openWindow("delete");
        });
        update.setOnAction(event -> {
            openWindow("id");
        });
        filter.setOnAction(event -> {
            openWindow("filter");
            objects_table.getScene().getWindow().hide();
        });

        name_field.setOnEditCommit((TableColumn.CellEditEvent<Vehicle, String> event) -> {
            TablePosition<Vehicle, String> pos = event.getTablePosition();
            String newName = event.getNewValue();
            int row = pos.getRow();
            Vehicle vehicle = event.getTableView().getItems().get(row);
            if (vehicle.getOwner().equals(loginText)) {
                vehicle.setName(newName);
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
