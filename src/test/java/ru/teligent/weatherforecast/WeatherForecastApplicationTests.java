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

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherForecastApplicationTests {

    @Autowired
    CityInformationRepository cityInformationRepository;

    private static final String urlWeatherCity = "http://bulk.openweathermap.org/sample/city.list.json.gz";
    private static final String locationDownload = "./src/main/city/"; /* Сюда записываеться путь куда будет сохраняться файл, а также куда будет распоковвывться архив расположения архива*/
    private static final String nameArchive = "city.list.json.gz";
    private List<CityInformation> cityInformationList = new ArrayList<>();

    @Test
    public void testDownload() {
        downloadFile();
    }

    @Test
    public void testUnarchive() {
        unarchive();
    }

    @Test
    public void testJsonReader() {
        json();
    }

    @Test
    public void testRepository() {
        System.out.println("Start!");
        json();
        addCityInformationList();
        System.out.println("End!");
        findAll();
    }

    private void downloadFile() {
        DownloaderFile archive = new DownloaderFile();
        archive.downloadUsingStream(urlWeatherCity, locationDownload + nameArchive);
    }

    private void unarchive() {
        ArchiveWorker archiveWorker = new ArchiveWorker();
        archiveWorker.unarchive(locationDownload + nameArchive);
    }

    private void json() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(locationDownload + "city.list.json"));
            JSONArray jsonArray = (JSONArray) obj;
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                CityInformation cityInformation = new CityInformation((long) jsonObject.get("id"), jsonObject.get("name").toString(), jsonObject.get("country").toString());
                cityInformationList.add(cityInformation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCityInformationList(){
        cityInformationRepository.saveAll(cityInformationList);
    }

    private void findAll(){
        List<CityInformation> cityInformationList1 = cityInformationRepository.findAll();
        System.out.println("");
    }
}
