package ru.teligent.weatherforecast.weather.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import ru.teligent.weatherforecast.cityDB.model.CityInformation;

import java.io.FileReader;

public class CreatorResponse {

    public CreatorResponse() {
    }

    public JsonResponse createJsonResponse(String json){

        JSONParser parser = new JSONParser();
        try {
            JSONObject weatherJsonObject = (JSONObject) JSONValue.parseWithException(json);
            JSONArray jsonArray = (JSONArray) weatherJsonObject.get("list");
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
