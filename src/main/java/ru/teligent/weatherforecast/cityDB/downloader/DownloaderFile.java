package ru.teligent.weatherforecast.cityDB.downloader;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloaderFile {

    public DownloaderFile() {
    }

    public void downloadUsingStream(String urlStr, String file) {
        try {
            URL url = new URL(urlStr);
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }
            fis.close();
            bis.close();
            System.out.println("Скачивание завершенно.");
        }
        catch (MalformedURLException e){
            System.out.println("URL указан не правильно.");
        }
        catch (IOException e){
            System.out.println("Не удалось скачать файл.");
        }
    }
}
