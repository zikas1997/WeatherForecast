package ru.teligent.weatherforecast.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class  VariableConfig {


    private static String urlWeatherCity;
    private static String locationDownload;
    private static String nameArchive;

    static {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/java/ru/teligent/weatherforecast/properties/config.properties");
            property.load(fis);
            urlWeatherCity = property.getProperty("url.URL_WEATHER_CITY");
            locationDownload = property.getProperty("path.LOCATION_DOWNLOAD");
            nameArchive = property.getProperty("path.NAME_ARCHIVE");

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }
    
    private VariableConfig() {
    }


    public static String getUrlWeatherCity() {
        return urlWeatherCity;
    }

    public static String getLocationDownload() {
        return locationDownload;
    }

    public static String getNameArchive() {
        return nameArchive;
    }
}
