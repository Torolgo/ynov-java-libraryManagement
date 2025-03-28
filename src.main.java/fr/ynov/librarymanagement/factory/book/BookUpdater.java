package fr.ynov.librarymanagement.factory.book;

import com.google.gson.reflect.TypeToken;

import fr.ynov.librarymanagement.domain.Bd;
import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.domain.Manga;
import fr.ynov.librarymanagement.domain.Novel;
import fr.ynov.librarymanagement.factory.FilesManagement;

import java.io.File;
import java.util.List;


public class BookUpdater extends FilesManagement {

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
        File file = new File(BASE_PATH + "novels.json");
        try {
            List<Novel> novels = readExistingData(file, new TypeToken<List<Novel>>() {}.getType());
            if (novels != null) {
                updateEntityStatus(novels, novel);
                writeToFile(file, novels);
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
        File file = new File(BASE_PATH + "novels.json");
        try {
            List<Bd> bds = readExistingData(file, new TypeToken<List<Bd>>() {}.getType());
            if (bds != null) {
                updateEntityStatus(bds, bd);
                writeToFile(file, bds);
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
        File file = new File(BASE_PATH + "mangas.json");
        try {
            List<Manga> mangas = readExistingData(file, new TypeToken<List<Manga>>() {}.getType());
            if (mangas != null) {
                updateEntityStatus(mangas, manga);
                writeToFile(file, mangas);
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