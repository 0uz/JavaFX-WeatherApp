package weather.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.lang3.text.WordUtils;
import weather.model.Weather;

import java.io.IOException;
import java.util.ArrayList;

public class MainScreenController {
    public ChoiceBox<String> unitChoiceBox;
    public ChoiceBox<String> langChoiceBox;
    public TextField cityTextField;
    public Button updateButton;
    public Label locationLabel;
    public Label tempLabel;
    public Label descLabel;
    public Label maks_tempLabel;
    public Label min_tempLabel;
    public Label pressLabel;
    public Label humidityLabel;
    public Label wind_speedLabel;
    public Label wind_degLabel;
    public Label cloudLabel;
    public Label warningLabel;
    public Label unit_tra_label;
    public Label lang_tra_label;
    public Label city_tra_label;
    public Label locat_tra_label;
    public Label temp_tra_label;
    public Label weather_tra_label;
    public Label min_temp_tra_label;
    public Label pressure_tra_label;
    public Label humidity_tra_label;
    public Label wing_speed_tra_label;
    public Label wind_degree_tra_label;
    public Label cloud_tra_label;
    public Label maks_temp_tra_label;
    public Label tempUnit;
    public Label maksTempUnit;
    public Label minTempUnit;
    public Label windSpeedUnit;
    public Label windDegreeUnit;
    public ImageView iconViewer;
    public Button exitButton;
    public Button minimizeButton;
    public TabPane tabPane;
    public Tab firstTab;
    public SplitPane splitPane;
    public BorderPane borderPane;
    private String lastCity="";
    ArrayList<Weather> weathers = new ArrayList<>();
    ArrayList<TabController> controllers = new ArrayList<>();

    public MainScreenController() throws IOException {

    }



    @FXML
    public void exitButton(){
        System.exit(0);
    }
    private void startTimer(){
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
                updateInfos();
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

    }

    @FXML
    public void initialize() {
        unitChoiceBox.getItems().add("İmperial");
        unitChoiceBox.getItems().add("Metric");
        unitChoiceBox.setValue("Metric");
        langChoiceBox.getItems().add("Türkçe");
        langChoiceBox.getItems().add("English");
        langChoiceBox.setValue("English");
        splitPane.setDividerPosition(0,1);
        startTimer();

    }

    @FXML
    private void updateAction(){
        updateInfos();

        }

        private void updateInfos(){
            String unit = unitChoiceBox.getValue().toLowerCase();
            String lang = langChoiceBox.getValue();

            for (int i=0;i<controllers.size();i++){
                Weather weather = weathers.get(i);
                controllers.get(i).updateLang(weather,lang);
                controllers.get(i).updateInfos(weather);
                controllers.get(i).updateUnit(unit,lang);
            }
        }


    public void updateIcon(String iconId){
        for (int i=0;i<controllers.size();i++){
            controllers.get(i).updateIcon(weathers.get(i).getIcon());
        }

    }




    @FXML
    public void minimize(){
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }



    @FXML
    void addTab() throws IOException {
    createTab();

    }

    public void createTab() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Tab.fxml"));
        Tab tab = loader.load();
        tabPane.getTabs().add(tab);
        TabController newTabController = loader.getController();


        String getCity = cityTextField.getText();
        String city= WordUtils.capitalizeFully(getCity);
        String unit = unitChoiceBox.getValue().toLowerCase();
        String lang;
        if (langChoiceBox.getValue().equals("Türkçe")){
            lang="tr";
        }else{
            lang="en" ;
        }

        if (getCity.length()<=1){
            if (langChoiceBox.getValue().equals("English")){
                warningLabel.setText("Please enter the city");
            }else{
                warningLabel.setText("Lütfen şehir giriniz!");
            }
            warningLabel.setVisible(true);
        }else{
            Weather weather = new Weather(unit,lang,city);
            if (weather.makeConnection()){
                controllers.add(newTabController);
                weathers.add(weather);
                updateIcon(weather.getIcon());
                warningLabel.setVisible(false);
                tab.setText(city);
                splitPane.setDividerPosition(0,0.32);

                newTabController.updateLang(weather,lang);
                newTabController.updateInfos(weather);

            }
        }


    }






}
