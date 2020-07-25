package weather.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import weather.model.Weather;

import java.io.File;
import java.io.IOException;

public class TabController {
    public Tab tab;
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
    public TabController() throws IOException {
    }

    @FXML
    void initialize(){

    }


    void updateLang(Weather weather,String lang){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Screen.fxml"));
        MainScreenController controller= loader.getController();
        if (weather.makeConnection()) {
            if ( lang.equals("Türkçe")){
                city_tra_label.setText("İl, İlçe");
                cloud_tra_label.setText("Bulut");
                humidity_tra_label.setText("Nem");
                lang_tra_label.setText("Dil");
                locat_tra_label.setText("Konum");
                min_temp_tra_label.setText("Minimum Sıcaklık");
                maks_temp_tra_label.setText("Maksimum Sıcaklık");
                unit_tra_label.setText("Birim");
                temp_tra_label.setText("Sıcaklık");
                weather_tra_label.setText("Durum");
                pressure_tra_label.setText("Basınç");
                wing_speed_tra_label.setText("Rüzgar Hızı");
                wind_degree_tra_label.setText("Rüzgar Derecesi");

                windDegreeUnit.setText("derece");

            }else if(lang.equals("English")){
                city_tra_label.setText("City, District");
                cloud_tra_label.setText("Cloudiness");
                humidity_tra_label.setText("Humidity");
                lang_tra_label.setText("Language");
                locat_tra_label.setText("Location");
                min_temp_tra_label.setText("Minimum Temperature");
                maks_temp_tra_label.setText("Maksimum Temperature");
                unit_tra_label.setText("Unit");
                temp_tra_label.setText("Temperature");
                weather_tra_label.setText("Weather Description");
                pressure_tra_label.setText("Pressure");
                wing_speed_tra_label.setText("Wind Speed");
                wind_degree_tra_label.setText("Wind Degree");

                windDegreeUnit.setText("degree");

            }
        }
        }

        void updateInfos(Weather weather){
            locationLabel.setText(weather.getLocation());
            tempLabel.setText(weather.getTemp());
            descLabel.setText(weather.getWeatherDes());
            maks_tempLabel.setText(weather.getTemp_max());
            min_tempLabel.setText(weather.getTemp_min());
            pressLabel.setText(weather.getPressure());
            humidityLabel.setText(weather.getHumidity());
            wind_speedLabel.setText(weather.getWind_speed());
            wind_degLabel.setText(weather.getWind_deg());
            cloudLabel.setText(weather.getCloudiness());
        }

        void updateUnit(String unit,String lang){
            if (unit.toLowerCase().equals("metric")){
                tempUnit.setText("°C");
                minTempUnit.setText("°C");
                maksTempUnit.setText("°C");
            }else{
                tempUnit.setText("°F");
                minTempUnit.setText("°F");
                maksTempUnit.setText("°F");
            }

            if (unit.toLowerCase().equals("metric")&&lang.equals("Türkçe")){
                windSpeedUnit.setText("metre/saniye");
            }
            if(unit.toLowerCase().equals("metric")&&lang.equals("English")){
                windSpeedUnit.setText("meter/sec");
            }
            if(unit.toLowerCase().equals("imperial")&&lang.equals("Türkçe")){
                windSpeedUnit.setText("mil/saat");
            }
            if (unit.toLowerCase().equals("imperial")&&lang.equals("English")){
                windSpeedUnit.setText("mile/hour");
            }
        }

    public void updateIcon(String iconId){
        File file = new File("src/Resources/"+iconId+"@2x.png");
        Image icon = new Image(file.toURI().toString());
        iconViewer.setImage(icon);
    }


}
