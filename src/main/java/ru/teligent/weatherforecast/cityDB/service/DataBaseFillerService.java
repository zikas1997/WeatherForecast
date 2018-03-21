package ru.teligent.weatherforecast.cityDB.service;


import org.springframework.beans.factory.annotation.Autowired;
import ru.teligent.weatherforecast.cityDB.archive.ArchiveWorker;
import ru.teligent.weatherforecast.cityDB.dao.CityInformationRepository;
import ru.teligent.weatherforecast.cityDB.downloader.DownloaderFile;
import ru.teligent.weatherforecast.cityDB.model.CityInformation;
import ru.teligent.weatherforecast.properties.VariableConfig;
import ru.teligent.weatherforecast.weather.json.CreatorCollectionCityInformationForJson;

import java.util.List;

public class DataBaseFillerService {

    @Autowired
    CityInformationRepository cityInformationRepository;

    public DataBaseFillerService() {
    }

    public void fillDataBase() {

        //Скачиваем файл
        DownloaderFile archive = new DownloaderFile();
        archive.downloadUsingStream( VariableConfig.getUrlWeatherCity(), VariableConfig.getLocationDownload() + VariableConfig.getNameArchive());

        //Разархивируем его
        ArchiveWorker archiveWorker = new ArchiveWorker();
        archiveWorker.unarchive(VariableConfig.getLocationDownload() + VariableConfig.getNameArchive());

        //Подготавливаем коллекцию для записи
        CreatorCollectionCityInformationForJson creatorCollectionCityInformationForJson = new CreatorCollectionCityInformationForJson();
        List<CityInformation> cityList = creatorCollectionCityInformationForJson.getCityList();

        //Запись в бд
        cityInformationRepository.saveAll(cityList);
    }

}
