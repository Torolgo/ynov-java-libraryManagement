package fr.ynov.librarymanagement.factory.book;

import com.google.gson.reflect.TypeToken;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Bd;
import fr.ynov.librarymanagement.domain.Genre;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Manga;
import fr.ynov.librarymanagement.domain.Novel;
import fr.ynov.librarymanagement.factory.FilesManagement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class BookWriter extends FilesManagement {
    /**
     * Writes a new novel to the JSON file.
     *
     * @param title The title of the novel.
     * @param author The author of the novel.
     * @param genre The genre of the novel.
     * @param year The year of publication.
     * @param pages The number of pages.
     * @param chapters The number of chapters.
     */
    public static void writeNovelFile(String title, Author author, Genre genre, int year, int pages, int chapters) {
        int nextId = BookFactory.getNextAvailableBookId();
        File file = new File(BASE_PATH + "novels.json");
        try {
            List<Novel> novels = readExistingData(file, new TypeToken<List<Novel>>() {}.getType());
            if (novels == null) {
                novels = new ArrayList<>();
            }

            novels.add(new Novel(nextId, title, author, genre, year, pages, chapters));
            writeToFile(file, novels);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a new BD to the JSON file.
     *
     * @param title The title of the BD.
     * @param author The author of the BD.
     * @param genre The genre of the BD.
     * @param year The year of publication.
     * @param pages The number of pages.
     * @param illustrator The illustrator of the BD.
     * @param illustrationStyle The style of illustration.
     */
    public static void writeBdFile(String title, Author author, Genre genre, int year, int pages,
                                   Illustrator illustrator, String illustrationStyle) {
        int nextId = BookFactory.getNextAvailableBookId();
        File file = new File(BASE_PATH + "bd.json");
        try {
            List<Bd> bds = readExistingData(file, new TypeToken<List<Bd>>() {}.getType());
            if (bds == null) {
                bds = new ArrayList<>();
            }

            bds.add(new Bd(nextId, title, author, genre, year, pages, illustrator, illustrationStyle));
            writeToFile(file, bds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a new manga to the JSON file.
     *
     * @param title The title of the manga.
     * @param author The author of the manga.
     * @param genre The genre of the manga.
     * @param year The year of publication.
     * @param pages The number of pages.
     * @param subGender The sub-genre of the manga.
     */
    public static void writeMangaFile(String title, Author author, Genre genre, int year, int pages, String subGender) {
        int nextId = BookFactory.getNextAvailableBookId();
        File file = new File(BASE_PATH + "manga.json");
        try {
            List<Manga> mangas = readExistingData(file, new TypeToken<List<Manga>>() {}.getType());
            if (mangas == null) {
                mangas = new ArrayList<>();
            }

            mangas.add(new Manga(nextId, title, author, genre, year, pages, subGender));
            writeToFile(file, mangas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}