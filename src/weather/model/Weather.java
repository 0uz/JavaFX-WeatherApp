package weather.model;

import javafx.scene.control.Label;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Weather {
    private static final String OWN_API_KEY = "5314680a836268eb2c4389322c0f3b7c";
    String weatherDes,temp,pressure,humidity,temp_min,temp_max,wind_speed,wind_deg,location,unit,lang,city,cloudiness,url,icon;


    public Weather(String unit, String lang, String city) {
        this.unit = unit;
        this.lang = lang;
        this.city = city;
        url =  "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + OWN_API_KEY + "&units="+unit+"&lang="+lang;
    }

    public Weather() {
        this.unit = "metric";
        this.lang = "tr";
        this.city = "Ankara";
        url =  "http://api.openweathermap.org/data/2.5/weather?q=" + "asdasdas" + "&appid=" + OWN_API_KEY + "&units="+unit+"&lang="+lang;
        System.out.println(url);

    }

    public String getWeatherDes() {
        return weatherDes;
    }

    public String getTemp() {
        return temp;
    }
    public boolean makeConnection(){
        try {
            StringBuilder result = new StringBuilder();
            java.net.URL url1 = new URL(url);
            URLConnection connection = url1.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                result.append(line);
            }
            reader.close();
            JSONObject resultJSON = new JSONObject(result.toString());
            fetch(resultJSON);
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            //warningLabel.setText("Please enter valid city name");
            //warningLabel.setVisible(true);
            return false;
        }
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public String getWind_deg() {
        return wind_deg;
    }

    public String getLocation() {
        return location;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public String getUrl() {
        return url;
    }

    public void fetch(JSONObject weather){
        weatherDes= weather.getJSONArray("weather").getJSONObject(0).get("description").toString();
        temp= weather.getJSONObject("main").get("temp").toString();
        location = city+", "+weather.getJSONObject("sys").get("country").toString();
        pressure = weather.getJSONObject("main").get("pressure").toString();
        humidity = weather.getJSONObject("main").get("humidity").toString();
        temp_min = weather.getJSONObject("main").get("temp_min").toString();
        temp_max = weather.getJSONObject("main").get("temp_max").toString();
        wind_speed = weather.getJSONObject("wind").get("speed").toString();
        wind_deg = weather.getJSONObject("wind").get("deg").toString();
        cloudiness = weather.getJSONObject("clouds").get("all").toString();
        icon = weather.getJSONArray("weather").getJSONObject(0).get("icon").toString();

    }

    public String getIcon() {
        return icon;
    }


}
