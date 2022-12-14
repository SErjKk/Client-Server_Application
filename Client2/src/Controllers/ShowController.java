package Controllers;
import Animation.AppearCircle;
import ClientSources.Client;
import Collection.Vehicle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

import static Controllers.AuthorizationController.loginText;
import static Controllers.HomeController.getList;
import static Controllers.HomeController.list;
import static javafx.scene.paint.Color.*;

public class ShowController {

    public static Vehicle vehicle;
    private Client client = new Client();

    @FXML
    private Button exit_button;

    @FXML
    private Label ownerLabel;

    @FXML
    private Button table_button;

    @FXML
    private Button restart_button;

    @FXML
    private Group group;

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

    //Генерация полей примитива
    public void generateX (Circle circle) {
        double x = ((Math.random()*773) + 1);
        circle.setCenterX(x);
    }
    public void generateY (Circle circle) {
        double y = ((Math.random()*266) + 1);
        circle.setCenterY(y);
    }
    public void generateColor (Circle circle, String user) {
        if (user.equals("test")) {
            circle.setFill(LIGHTGREEN);
            circle.setStroke(DARKGREEN);
        }
        if (user.equals("admin")) {
            circle.setFill(LIGHTPINK);
            circle.setStroke(DARKRED);
        }
        if (user.equals("serjk")) {
            circle.setFill(LIGHTBLUE);
            circle.setStroke(DARKBLUE);
        }
    }
    public Color generate() {
        int r = (int) (Math.random()*255);
        int g = (int) (Math.random()*255);
        int b = (int) (Math.random()*255);
        Color color = new Color(r,g,b,0);

        return color;
    }

    //Инициализация рисовалки
    public void initPane() {
        list = getList();
        group.getChildren().clear();

        for (int i = 0; i < list.size(); i++) {
            Circle circle = new Circle();
            double R = (list.get(i).getCapacity() + 5) * 0.5;
            String user = (list.get(i).getOwner());
            circle.setRadius(R);
            circle.setStrokeWidth(0.3);

            generateColor(circle, user);
            generateX(circle);
            generateY(circle);

            while (circle.getRadius()*10 - circle.getCenterX() >= 0)
                generateX(circle);
            while (circle.getRadius()*10 - circle.getCenterY() >= 0)
                generateY(circle);

            String name = list.get(i).getName();
            Text text = new Text(name);
            text.setX(circle.getCenterX()-name.length()*2.4);
            text.setY(circle.getCenterY()+5);

            circle.setUserData(list.get(i));
            group.getChildren().addAll(circle, text);

            AppearCircle animation = new AppearCircle(circle);
            animation.playAnimation();

            circle.setOnMouseClicked(event -> {
                vehicle = (Vehicle) circle.getUserData();
                openWindow("info");
            });
        }
    }

    @FXML
    void initialize() {
        ownerLabel.setText(client.getProperties().getProperty("Label_4"));
        table_button.setText(client.getProperties().getProperty("Button_8"));
        restart_button.setText(client.getProperties().getProperty("Button_9"));
        exit_button.setText(client.getProperties().getProperty("Button_10"));

        initPane();

        ownerLabel.setText(ownerLabel.getText() + loginText);

        exit_button.setOnAction(event -> {
            exit_button.getScene().getWindow().hide();
            openWindow("authorization");
        });

        table_button.setOnAction(event -> {
            table_button.getScene().getWindow().hide();
            openWindow("home");
        });

        restart_button.setOnAction(event -> {
            initPane();
        });
    }
}