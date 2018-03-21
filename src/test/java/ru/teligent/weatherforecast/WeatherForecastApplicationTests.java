package ru.teligent.weatherforecast;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.teligent.weatherforecast.cityDB.archive.ArchiveWorker;
import ru.teligent.weatherforecast.cityDB.dao.CityInformationRepository;
import ru.teligent.weatherforecast.cityDB.downloader.DownloaderFile;
import ru.teligent.weatherforecast.cityDB.model.CityInformation;
import ru.teligent.weatherforecast.properties.VariableConfig;
import ru.teligent.weatherforecast.weather.http.HttpApacheClient;
import ru.teligent.weatherforecast.weather.json.CreatorCollectionCityInformationForJson;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherForecastApplicationTests {

    @Autowired
    CityInformationRepository cityInformationRepository;


    private List<CityInformation> cityInformationList = new ArrayList<>();

    // @Test
    public void testDownload() {
        downloadFile();
    }

    // @Test
    public void testUnarchive() {
        unarchive();
    }

    //   @Test
    public void testJsonReader() {
        json();
    }

    //@Test
    public void testRepository() {
        System.out.println("Start!");
        json();
        addCityInformationList();
        System.out.println("End!");
    }

    //@Test
    public void testConfig() {
        System.out.println("getLocationDownload() " + VariableConfig.getLocationDownload());

        System.out.println("getUrlWeatherCity() " + VariableConfig.getUrlWeatherCity());

        System.out.println("getNameArchive() " + VariableConfig.getNameArchive());
    }

    @Test
    public void testRequestOpenweathermap() {
        HttpApacheClient httpApacheClient = new HttpApacheClient();
        String response = "";
        try {
            response = httpApacheClient.getResponse("http://api.openweathermap.org/data/2.5/forecast?id=542420&appid=5416f954bc04f165f0a04802d9cdb2a6");
        } catch (IOException e) {
            System.out.println("Нет доступа к сервису.");
        }
        System.out.println("Response json: " + response);
    }


    private void downloadFile() {
        DownloaderFile archive = new DownloaderFile();
        archive.downloadUsingStream(VariableConfig.getUrlWeatherCity(), VariableConfig.getLocationDownload() + VariableConfig.getNameArchive());
    }

    private void unarchive() {
        ArchiveWorker archiveWorker = new ArchiveWorker();
        archiveWorker.unarchive(VariableConfig.getLocationDownload() + VariableConfig.getNameArchive());
    }

    private void json() {
        CreatorCollectionCityInformationForJson creatorCollectionCityInformationForJson = new CreatorCollectionCityInformationForJson();
        cityInformationList = creatorCollectionCityInformationForJson.getCityList();
    }

    private void addCityInformationList() {
        cityInformationRepository.saveAll(cityInformationList);
    }

    private void findAll() {
        List<CityInformation> cityInformationList1 = cityInformationRepository.findAll();
    }
}
