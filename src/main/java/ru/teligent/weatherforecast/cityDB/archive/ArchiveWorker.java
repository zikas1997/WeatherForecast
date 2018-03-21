package ru.teligent.weatherforecast.cityDB.archive;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ArchiveWorker {

    public ArchiveWorker() {

    }

    public void unarchive(String locationZipFile) {
        byte[] buffer = new byte[1024];
        try (GZIPInputStream gzipis = new GZIPInputStream(
                new FileInputStream(locationZipFile));
             FileOutputStream fos = new FileOutputStream(
                     destinationDirectory(locationZipFile))) {
            int length = 0;
            while ((length = gzipis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден или имя файла указано не верно.");
        } catch (IOException ex) {
            System.out.println("Не удаеться открыть архив");
        }
    }

    private String destinationDirectory(final String srcZip) {
        return srcZip.substring(0, srcZip.lastIndexOf("."));
    }
}
