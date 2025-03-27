package fr.ynov.librarymanagement.factory;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.domain.Bd;
import fr.ynov.librarymanagement.domain.Manga;
import fr.ynov.librarymanagement.domain.Novel;
import fr.ynov.librarymanagement.domain.Genre;
import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class BookFactory {
    private static final ArrayList<Book> bookList = new ArrayList<>();

    public static List<Book> getBookList() {
        return bookList;
    }
    public static void clearBookList() {
        bookList.clear();
    }

    public static void readNovelsFile() {
        Gson gson = new Gson();
        List<Novel> novelJson;
        try {
            File file = new File("src.main.java/fr/ynov/librarymanagement/assets/novels.json");
            if (file.exists() && file.length() > 0) {
                novelJson = gson.fromJson(new FileReader(file), new TypeToken<List<Novel>>() {
                }.getType());
                if (novelJson != null) {
                    bookList.addAll(novelJson);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readBdsFile() {
        Gson gson = new Gson();
        List<Bd> bdJson;
        try {
            File file = new File("src.main.java/fr/ynov/librarymanagement/assets/bd.json");
            if (file.exists() && file.length() > 0) {
                bdJson = gson.fromJson(new FileReader(file), new TypeToken<List<Bd>>() {
                }.getType());
                if (bdJson != null) {
                    bookList.addAll(bdJson);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readMangasFile() {
        Gson gson = new Gson();
        List<Manga> mangaJson;
        try {
            File file = new File("src.main.java/fr/ynov/librarymanagement/assets/manga.json");
            if (file.exists() && file.length() > 0) {
                mangaJson = gson.fromJson(new FileReader(file), new TypeToken<List<Manga>>() {
                }.getType());
                if (mangaJson != null) {
                    bookList.addAll(mangaJson);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeNovelsFile(String title, Author author, Genre genre, int year, int pages, int chapters) {
        int nextId = getNextAvailableBookId();
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/novels.json");
        List<Novel> novelJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                novelJson = gson.fromJson(new FileReader(file), new TypeToken<List<Novel>>() {
                }.getType());
            }
            if (novelJson == null) {
                novelJson = new ArrayList<>();
            }
            novelJson.add(new Novel(nextId, title, author, genre, year, pages, chapters));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(novelJson));
            fileWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeBdsFile(String title, Author author, Genre genre, int year, int pages, Illustrator illustrator, String illustrationStyle) {
        int nextId = getNextAvailableBookId();
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/bd.json");
        List<Bd> bdJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                bdJson = gson.fromJson(new FileReader(file), new TypeToken<List<Bd>>() {
                }.getType());
            }
            if (bdJson == null) {
                bdJson = new ArrayList<>();
            }
            bdJson.add(new Bd(nextId, title, author, genre, year, pages, illustrator, illustrationStyle));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(bdJson));
            fileWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeMangasFile(String title, Author author, Genre genre, int year, int pages, String subGender) {
        int nextId = getNextAvailableBookId();
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/manga.json");
        List<Manga> mangaJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                mangaJson = gson.fromJson(new FileReader(file), new TypeToken<List<Manga>>() {
                }.getType());
            }
            if (mangaJson == null) {
                mangaJson = new ArrayList<>();
            }
            mangaJson.add(new Manga(nextId, title, author, genre, year, pages, subGender));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(mangaJson));
            fileWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateBookStatus(Book book) {
        if (book instanceof Novel) {
            updateNovelStatus((Novel) book);
        } else if (book instanceof Bd) {
            updateBdStatus((Bd) book);
        } else if (book instanceof Manga) {
            updateMangaStatus((Manga) book);
        }
    }

    private static void updateNovelStatus(Novel novel) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/novels.json");
        List<Novel> novelJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                novelJson = gson.fromJson(new FileReader(file), new TypeToken<List<Novel>>() {
                }.getType());
            }
            if (novelJson == null) {
                return;
            }

            for (Novel n : novelJson) {
                if (n.getId() == novel.getId()) {
                    n.takeBook();  // Will be set to true/false based on actual status
                    if (!novel.isTaken()) {
                        n.returnBook();
                    }
                    break;
                }
            }

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(novelJson));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateBdStatus(Bd bd) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/bd.json");
        List<Bd> bdJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                bdJson = gson.fromJson(new FileReader(file), new TypeToken<List<Bd>>() {
                }.getType());
            }
            if (bdJson == null) {
                return;
            }

            for (Bd b : bdJson) {
                if (b.getId() == bd.getId()) {
                    b.takeBook();  // Will be set to true
                    if (!bd.isTaken()) {
                        b.returnBook();
                    }
                    break;
                }
            }

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(bdJson));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateMangaStatus(Manga manga) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/manga.json");
        List<Manga> mangaJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                mangaJson = gson.fromJson(new FileReader(file), new TypeToken<List<Manga>>() {
                }.getType());
            }
            if (mangaJson == null) {
                return;
            }

            for (Manga m : mangaJson) {
                if (m.getId() == manga.getId()) {
                    m.takeBook();  // Will be set to true
                    if (!manga.isTaken()) {
                        m.returnBook();
                    }
                    break;
                }
            }

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(mangaJson));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // New method to get the next available ID
    private static int getNextAvailableBookId() {
        int maxId = 0;

        // Clear and reload all books to ensure we have the latest data
        clearBookList();
        readNovelsFile();
        readBdsFile();
        readMangasFile();

        // Find the maximum ID currently in use
        for (Book book : bookList) {
            if (book.getId() > maxId) {
                maxId = book.getId();
            }
        }

        // Return max ID + 1
        return maxId + 1;
    }

}