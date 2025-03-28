package fr.ynov.librarymanagement.factory.book;

import com.google.gson.reflect.TypeToken;

import fr.ynov.librarymanagement.domain.Bd;
import fr.ynov.librarymanagement.domain.Manga;
import fr.ynov.librarymanagement.domain.Novel;
import fr.ynov.librarymanagement.factory.FilesManagement;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class BookReader extends FilesManagement {

    /**
     * Loads novels from JSON file.
     */
    public static void readNovelsFile() {
        try {
            File file = new File(BASE_PATH + "novels.json");
            if (isValidFile(file)) {
                List<Novel> novelJson = GSON.fromJson(new FileReader(file), new TypeToken<List<Novel>>() {
                }.getType());
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
                List<Bd> bdJson = GSON.fromJson(new FileReader(file), new TypeToken<List<Bd>>() {
                }.getType());
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
                List<Manga> mangaJson = GSON.fromJson(new FileReader(file), new TypeToken<List<Manga>>() {
                }.getType());
                if (mangaJson != null) {
                    BookFactory.getBookList().addAll(mangaJson);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}