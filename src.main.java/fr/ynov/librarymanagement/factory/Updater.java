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
import java.lang.reflect.Type;
import java.util.List;

public class Updater extends FilesManagement {
    /**
     * Updates a book's status in the corresponding JSON file.
     * <p>
     * This method determines the appropriate JSON file based on the book's type (Novel, Bd, or Manga),
     * reads the existing data from that file, updates the status of the specified book,
     * and writes the updated data back to the file.
     * </p>
     *
     * @param book The book whose status needs to be updated
     */
    public static void updateBookStatus(Book book) {
        String fileName;
        switch (book) {
            case Novel ignored -> fileName = "novels.json";
            case Bd ignored -> fileName = "bds.json";
            case Manga ignored -> fileName = "mangas.json";
            case null, default -> {
                assert book != null;
                System.err.println("Type de livre non pris en charge: " + book.getClass().getSimpleName());
                return;
            }
        }

        File file = new File(BASE_PATH + fileName);
        try {
            List<? extends Book> books = readExistingData(file, TypeToken.getParameterized(List.class, book.getClass()).getType());
            if (books != null) {
                updateEntityStatus((List) books, book);
                writeToFile(file, books);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the status of a book entity in a list.
     * <p>
     * This method finds a book with matching ID in the provided list and updates its status
     * (taken or returned) to match that of the target entity. This is used as a helper
     * method for updating book status in storage.
     * </p>
     *
     * @param <T> The type parameter extending Book
     * @param entities The list of book entities to search through
     * @param targetEntity The book entity containing the updated status
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

    /**
     * Updates a person's biography and saves it to the appropriate JSON file.
     * <p>
     * This method updates the biography of the specified person (either Author or Illustrator),
     * determines the correct JSON file based on the person's type, reads the existing data,
     * updates the person's biography in the list, and writes the updated data back to the file.
     * </p>
     *
     * @param person The person whose biography needs to be updated
     * @param newBiography The new biography text to set for the person
     */
    public static void updateBiography(Person person, String newBiography) {
        person.setBiography(newBiography);
        String fileName;
        Type typeToken;

        if (person instanceof Author) {
            fileName = "authors.json";
            typeToken = new TypeToken<List<Author>>() {}.getType();
        } else if (person instanceof Illustrator) {
            fileName = "illustrators.json";
            typeToken = new TypeToken<List<Illustrator>>() {}.getType();
        } else {
            return;
        }

        // Read the existing data from the file
        File file = new File(BASE_PATH + fileName);
        List<?> personList = readExistingData(file, typeToken);
        if (personList == null) {
            return;
        }

        boolean found = false;
        // Search for the person with the corresponding ID
        for (Object p : personList) {
            Person currentPerson = (Person) p;
            if (currentPerson.getId() == person.getId()) {
                currentPerson.setBiography(newBiography);
                found = true;
                break;
            }
        }

        // If found, write the updated list back to the file
        if (found) {
            try {
                writeToFile(file, personList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
