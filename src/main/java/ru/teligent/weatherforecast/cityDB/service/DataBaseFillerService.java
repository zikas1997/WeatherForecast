package ru.teligent.weatherforecast.cityDB.service;


import ru.teligent.weatherforecast.cityDB.archive.ArchiveWorker;
import ru.teligent.weatherforecast.cityDB.downloader.DownloaderFile;

public class DataBaseFillerService {

    private static final String url = "http://bulk.openweathermap.org/sample/city.list.json.gz";
    private static String location = "./src/main/resources/"; /* Сюда записываеться путь куда будет сохраняться файл, а также куда будет распоковвывться архив расположения архива*/
    private static final String nameArchive = "city.list.json.gz";

    public DataBaseFillerService() {
    }

    public void fillDataBase() {

        //Скачиваем файл
        DownloaderFile archive = new DownloaderFile();
        archive.downloadUsingStream( url, location + nameArchive);

        //Разархивируем его
        ArchiveWorker archiveWorker = new ArchiveWorker();
        archiveWorker.unarchive(location + nameArchive);

        //Подготавливаем коллекцию для записи

    }

}
