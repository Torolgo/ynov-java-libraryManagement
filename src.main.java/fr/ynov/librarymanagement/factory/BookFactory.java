package fr.ynov.librarymanagement.factory;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import fr.ynov.librarymanagement.domain.*;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class BookFactory {
    private static final ArrayList<Book> bookList = new ArrayList<Book>();

    public static void ReadNovelsFile() {
        Gson gson = new Gson();
        List<Novel> novelJson = null;
        try {
            novelJson = gson.fromJson(new FileReader("src.main.java/fr/ynov/librarymanagement/assets/novels.json"), new TypeToken<List<Novel>>() {
            }.getType());
            bookList.addAll(novelJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void ReadBdsFile() {
        Gson gson = new Gson();
        List<Bd> bdJson = null;
        try {
            bdJson = gson.fromJson(new FileReader("src.main.java/fr/ynov/librarymanagement/assets/bd.json"), new TypeToken<List<Bd>>() {
            }.getType());
            bookList.addAll(bdJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void ReadMangasFile() {
        Gson gson = new Gson();
        List<Manga> mangaJson = null;
        try {
            mangaJson = gson.fromJson(new FileReader("src.main.java/fr/ynov/librarymanagement/assets/manga.json"), new TypeToken<List<Manga>>() {
            }.getType());
            bookList.addAll(mangaJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void WriteNovelsFile(int id, String title, Author author, Genre genre, int year, int pages, int chapters) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/novels.json");
        List<Novel> novelJson = null;
        try {
            if (file.exists() && file.length() > 0) {
                novelJson = gson.fromJson(new FileReader(file), new TypeToken<List<Novel>>() {
                }.getType());
            }
            assert novelJson != null;
            novelJson.forEach(novel -> {
                if (novel.getId() == id) {
                    throw new IllegalArgumentException("This id already exists");
                }
            });
            novelJson.add(new Novel(id, title, author, genre, year, pages, chapters));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(novelJson));
            fileWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void WriteBdsFile(int id, String title, Author author, Genre genre, int year, int pages, Illustrator illustrator, String illustrationStyle) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/bd.json");
        List<Bd> bdJson = null;
        try {
            if (file.exists() && file.length() > 0) {
                bdJson = gson.fromJson(new FileReader(file), new TypeToken<List<Bd>>() {
                }.getType());
            }
            assert bdJson != null;
            bdJson.forEach(bd -> {
                if (bd.getId() == id) {
                    throw new IllegalArgumentException("This id already exists");
                }
            });
            bdJson.add(new Bd(id, title, author, genre, year, pages, illustrator, illustrationStyle));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(bdJson));
            fileWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void WriteMangasFile(int id, String title, Author author, Genre genre, int year, int pages, String subGender) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/manga.json");
        List<Manga> mangaJson = null;
        try {
            if (file.exists() && file.length() > 0) {
                mangaJson = gson.fromJson(new FileReader(file), new TypeToken<List<Manga>>() {
                }.getType());
            }
            assert mangaJson != null;
            mangaJson.forEach(manga -> {
                if (manga.getId() == id) {
                    throw new IllegalArgumentException("This id already exists");
                }
            });
            mangaJson.add(new Manga(id, title, author, genre, year, pages, subGender));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(mangaJson));
            fileWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Book> getBookList() {
        return bookList;
    }
}