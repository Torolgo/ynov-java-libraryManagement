package fr.ynov.librarymanagement.factory;

import com.google.gson.reflect.TypeToken;
import fr.ynov.librarymanagement.domain.Person;
import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.domain.Bd;
import fr.ynov.librarymanagement.domain.Manga;
import fr.ynov.librarymanagement.domain.Novel;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import static fr.ynov.librarymanagement.factory.BookFactory.clearBookList;
import static fr.ynov.librarymanagement.factory.PersonFactory.clearPersonList;

public class Reader extends FilesManagement {

    /**
     * Reads a JSON file containing a list of books (either novels, mangas, or BDs)
     * and adds them to the in-memory collection of books.
     * <p>
     * This method is generic and can handle all book types.
     * It uses the provided TypeToken to deserialize the JSON data into the appropriate list type.
     * </p>
     *
     * @param <T> The type of book (Novel, Manga, or Bd)
     * @param filename The name of the JSON file to read
     * @param typeToken The TypeToken for the specific type of book
     */
    private static <T extends Book> void readBookFile(String filename, TypeToken<List<T>> typeToken) {
        List<T> bookList;
        try {
            File file = new File(BASE_PATH + filename);
            if (isValidFile(file)) {
                bookList = GSON.fromJson(new FileReader(file), typeToken.getType());
                if (bookList != null) {
                    BookFactory.getBookList().addAll(bookList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the JSON files for novels, mangas, and BDs and loads them into the in-memory collection.
     * <p>
     * This method clears the existing book list before loading the new data.
     * </p>
     */
    public static void loadAllBooks() {
        clearBookList();
        readBookFile("novels.json", new TypeToken<List<Novel>>() {});
        readBookFile("mangas.json", new TypeToken<List<Manga>>() {});
        readBookFile("bds.json", new TypeToken<List<Bd>>() {});
    }

    /**
     * Reads a JSON file containing a list of persons (either authors or illustrators)
     * and adds them to the in-memory collection of persons.
     * <p>
     * This method is generic and can handle both Author and Illustrator types.
     * It uses the provided TypeToken to deserialize the JSON data into the appropriate list type.
     * </p>
     *
     * @param <T> The type of person (Author or Illustrator)
     * @param filename The name of the JSON file to read
     * @param typeToken The TypeToken for the specific type of person
     */
    public static <T extends Person> void readPersonFile(String filename, TypeToken<List<T>> typeToken) {
        List<T> personList;
        try {
            File file = new File(BASE_PATH + filename);
            if (isValidFile(file)) {
                personList = GSON.fromJson(new FileReader(file), typeToken.getType());
                if (personList != null) {
                    PersonFactory.getPersonList().addAll(personList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the JSON files for authors and illustrators and loads them into the in-memory collection.
     * <p>
     * This method clears the existing person list before loading the new data.
     * </p>
     */
    public static void loadAllPersons() {
        clearPersonList();
        readPersonFile("authors.json", new TypeToken<List<Author>>() {});
        readPersonFile("illustrators.json", new TypeToken<List<Illustrator>>() {});
    }
}
