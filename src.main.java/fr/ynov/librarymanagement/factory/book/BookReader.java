package fr.ynov.librarymanagement.factory.book;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.ynov.librarymanagement.domain.Bd;
import fr.ynov.librarymanagement.domain.Manga;
import fr.ynov.librarymanagement.domain.Novel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BookReader {
    private static final String BASE_PATH = "src.main.java/fr/ynov/librarymanagement/assets/";
    private static final Gson gson = new Gson();


    /**
     * Loads novels from JSON file.
     */
    public static void readNovelsFile() {
        try {
            File file = new File(BASE_PATH + "novels.json");
            if (isValidFile(file)) {
                java.util.List<Novel> novelJson = gson.fromJson(new FileReader(file),
                        new TypeToken<java.util.List<Novel>>() {}.getType());
                if (novelJson != null) {
                    BookFactory.getBookList().addAll(novelJson);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads BD from JSON file.
     */
    public static void readBdsFile() {
        try {
            File file = new File(BASE_PATH + "bd.json");
            if (isValidFile(file)) {
                java.util.List<Bd> bdJson = gson.fromJson(new FileReader(file),
                        new TypeToken<java.util.List<Bd>>() {}.getType());
                if (bdJson != null) {
                    BookFactory.getBookList().addAll(bdJson);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads manga from JSON file.
     */
    public static void readMangasFile() {
        try {
            File file = new File(BASE_PATH + "manga.json");
            if (isValidFile(file)) {
                java.util.List<Manga> mangaJson = gson.fromJson(new FileReader(file),
                        new TypeToken<java.util.List<Manga>>() {}.getType());
                if (mangaJson != null) {
                    BookFactory.getBookList().addAll(mangaJson);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads existing data from a file.
     *
     * @param fileName The name of the file to read.
     * @param type The type of data to read.
     * @return The data read from the file.
     */
    public static <T> T readExistingData(String fileName, java.lang.reflect.Type type) {
        File file = new File(BASE_PATH + fileName);
        try {
            if (file.exists() && file.length() > 0) {
                return gson.fromJson(new FileReader(file), type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if the file exists and is not empty.
     */
    private static boolean isValidFile(File file) {
        return file.exists() && file.length() > 0;
    }
}