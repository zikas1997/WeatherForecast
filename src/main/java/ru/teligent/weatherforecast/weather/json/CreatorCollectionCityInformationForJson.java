package ru.teligent.weatherforecast.weather.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.teligent.weatherforecast.cityDB.model.CityInformation;
import ru.teligent.weatherforecast.properties.VariableConfig;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CreatorCollectionCityInformationForJson {

    private List<CityInformation> cityList = new ArrayList<>();

    public CreatorCollectionCityInformationForJson() {
    }

    public List<CityInformation> getCityList() {
        createCityInformationList();
        return cityList;
    }

    private void createCityInformationList(){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(VariableConfig.getLocationDownload() + "city.list.json"));
            JSONArray jsonArray = (JSONArray) obj;
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                CityInformation cityInformation = new CityInformation((long) jsonObject.get("id"), jsonObject.get("name").toString(), jsonObject.get("country").toString());
                cityList.add(cityInformation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
