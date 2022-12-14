package Controllers;
import ClientSources.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static Animation.ShakeField.errorStyle;

public class LanguageController {

    ObservableList<String> languages = FXCollections.observableArrayList("Русский (RU)", "Беларускі (BY)", "\u0395\u03BB\u03BB\u03B7\u03BD\u03B9\u03BA\u03AE (EL)", "Espa\u00F1ol (ES)");
    private Properties properties = new Properties();
    private Client client = new Client();
    String path = "E:/SERJK/ITMO/Программирование/LabEight/Client2/src/LocalFiles/";

    @FXML
    private Button button;

    @FXML
    private ComboBox<String> languageBox;

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
        switch (languageBox.getValue()) {
            case "Русский (RU)":
                try {
                    properties.load(new InputStreamReader(new FileInputStream(path + "RU.properties"), StandardCharsets.UTF_8));
                    client.setProperties(properties);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "Беларускі (BY)":
                try {
                    properties.load(new InputStreamReader(new FileInputStream(path + "BY.properties"), StandardCharsets.UTF_8));
                    client.setProperties(properties);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "\u0395\u03BB\u03BB\u03B7\u03BD\u03B9\u03BA\u03AE (EL)":
                try {
                    properties.load(new InputStreamReader(new FileInputStream(path + "EL.properties"), StandardCharsets.UTF_8));
                    client.setProperties(properties);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "Espa\u00F1ol (ES)":
                try {
                    properties.load(new InputStreamReader(new FileInputStream(path + "ES.properties"), StandardCharsets.UTF_8));
                    client.setProperties(properties);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @FXML
    void initialize() {
        languageBox.setItems(languages);

        button.setOnAction(event -> {
            try {
                getText();
                button.getScene().getWindow().hide();
                openWindow("authorization");
            } catch (NullPointerException e) {
                languageBox.setStyle(errorStyle);
            }
        });
    }
}