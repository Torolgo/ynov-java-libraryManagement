package fr.ynov.librarymanagement.factory.book;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Bd;
import fr.ynov.librarymanagement.domain.Genre;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Manga;
import fr.ynov.librarymanagement.domain.Novel;
import static fr.ynov.librarymanagement.factory.book.BookReader.readExistingData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BookWriter {
    private static final String BASE_PATH = "src.main.java/fr/ynov/librarymanagement/assets/";
    private static final Gson gson = new Gson();

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
        try {
            List<Novel> novels = readExistingData("novels.json", new TypeToken<List<Novel>>() {}.getType());
            if (novels == null) {
                novels = new ArrayList<>();
            }

            novels.add(new Novel(nextId, title, author, genre, year, pages, chapters));
            writeToFile("novels.json", novels);
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
        try {
            List<Bd> bds = readExistingData("bd.json", new TypeToken<List<Bd>>() {}.getType());
            if (bds == null) {
                bds = new ArrayList<>();
            }

            bds.add(new Bd(nextId, title, author, genre, year, pages, illustrator, illustrationStyle));
            writeToFile("bd.json", bds);
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
        try {
            List<Manga> mangas = readExistingData("manga.json", new TypeToken<List<Manga>>() {}.getType());
            if (mangas == null) {
                mangas = new ArrayList<>();
            }

            mangas.add(new Manga(nextId, title, author, genre, year, pages, subGender));
            writeToFile("manga.json", mangas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes data to a file.
     *
     * @param fileName The name of the file to write to.
     * @param data The data to write.
     * @throws IOException If an I/O error occurs.
     */
    public static <T> void writeToFile(String fileName, T data) throws IOException {
        try (FileWriter fileWriter = new FileWriter(BASE_PATH + fileName)) {
            fileWriter.write(gson.toJson(data));
        }
    }
}