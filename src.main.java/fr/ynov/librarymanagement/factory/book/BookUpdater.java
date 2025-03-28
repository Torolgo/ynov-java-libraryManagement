package fr.ynov.librarymanagement.factory.book;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.ynov.librarymanagement.domain.Bd;
import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.domain.Manga;
import fr.ynov.librarymanagement.domain.Novel;
import static fr.ynov.librarymanagement.factory.book.BookReader.readExistingData;

import java.util.List;


public class BookUpdater {
    private static final String BASE_PATH = "src.main.java/fr/ynov/librarymanagement/assets/";
    private static final Gson gson = new Gson();

    /**
     * Updates the status of a book (taken or returned).
     *
     * @param book The book to update.
     */
    public static void updateBookStatus(Book book) {
        if (book instanceof Novel) {
            updateNovelStatus((Novel) book);
        } else if (book instanceof Bd) {
            updateBdStatus((Bd) book);
        } else if (book instanceof Manga) {
            updateMangaStatus((Manga) book);
        }
    }

    /**
     * Updates the status of a novel.
     *
     * @param novel The novel to update.
     */
    private static void updateNovelStatus(Novel novel) {
        try {
            List<Novel> novels = readExistingData("novels.json", new TypeToken<List<Novel>>() {}.getType());
            if (novels != null) {
                updateEntityStatus(novels, novel);
                BookWriter.writeToFile("novels.json", novels);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the status of a BD.
     *
     * @param bd The BD to update.
     */
    private static void updateBdStatus(Bd bd) {
        try {
            List<Bd> bds = readExistingData("bd.json", new TypeToken<List<Bd>>() {}.getType());
            if (bds != null) {
                updateEntityStatus(bds, bd);
                BookWriter.writeToFile("bd.json", bds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the status of a manga.
     *
     * @param manga The manga to update.
     */
    private static void updateMangaStatus(Manga manga) {
        try {
            List<Manga> mangas = readExistingData("manga.json", new TypeToken<List<Manga>>() {}.getType());
            if (mangas != null) {
                updateEntityStatus(mangas, manga);
                BookWriter.writeToFile("manga.json", mangas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the status of a book entity in the list.
     *
     * @param entities The list of entities.
     * @param targetEntity The entity to update.
     */
    private static <T extends Book> void updateEntityStatus(List<T> entities, T targetEntity) {
        for (T entity : entities) {
            if (entity.getId() == targetEntity.getId()) {
                if (targetEntity.isTaken()) {
                    entity.takeBook();
                } else {
                    entity.returnBook();
                }
                break;
            }
        }
    }

}