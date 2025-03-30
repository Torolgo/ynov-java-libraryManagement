package fr.ynov.librarymanagement.factory;

import com.google.gson.internal.LinkedTreeMap;
import fr.ynov.librarymanagement.domain.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

import static fr.ynov.librarymanagement.factory.Reader.loadAllBooks;
import static fr.ynov.librarymanagement.factory.Reader.loadAllPersons;

public class Deleter extends FilesManagement{

    /**
     * Deletes an entity (book or person) from its corresponding JSON file.
     * <p>
     * This method reads the JSON file, filters out the entity with the specified ID,
     * and writes the updated list back to the file. After deletion, it reloads the
     * appropriate data collection.
     * </p>
     *
     * @param file The JSON file containing the entity to delete
     * @param idToDelete The ID of the entity to be deleted
     * @param isPerson Boolean flag indicating if the entity is a person (true) or a book (false)
     * @throws Exception If the file doesn't exist or there are issues with reading/writing
     */
    private static void deleteEntity(File file, int idToDelete, boolean isPerson) throws Exception {
        if (!file.exists()) {
            throw new Exception("Fichier introuvable: " + file.getName());
        }

        List<?> entities = GSON.fromJson(new FileReader(file), List.class);
        if (entities == null) {
            return;
        }

        // Filter out the entity with the specified ID
        List<?> updatedEntities = entities.stream()
                .filter(entity -> {
                    // Extract the ID, handling potential decimal format from JSON parsing
                    int id = ((LinkedTreeMap<?, ?>) entity).get("id").toString().contains(".")
                            ? (int) Double.parseDouble(((LinkedTreeMap<?, ?>) entity).get("id").toString())
                            : Integer.parseInt(((LinkedTreeMap<?, ?>) entity).get("id").toString());
                    return id != idToDelete;
                })
                .collect(Collectors.toList());

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(GSON.toJson(updatedEntities));
        }

        if (isPerson) {
            loadAllPersons();
        } else {
            loadAllBooks();
        }
    }

    /**
     * Checks if a person (author or illustrator) is referenced in any books.
     * <p>
     * This method prevents deletion of people who are associated with books,
     * maintaining referential integrity in the system.
     * </p>
     *
     * @param person The person to check for references
     * @return true if the person is referenced in any book, false otherwise
     */
    public static boolean isPersonReferencedInBooks(Person person) {
        for (Book book : BookFactory.getBookList()) {
            if (book.getAuthor().getId() == person.getId()) {
                return true;
            }

            if (book instanceof Bd bd &&
                    bd.getIllustrator() != null &&
                    bd.getIllustrator().getId() == person.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a person (author or illustrator) from the system.
     * <p>
     * This method determines the appropriate file based on the person's type
     * and calls deleteEntity to remove them from the file.
     * </p>
     *
     * @param personToDelete The person to be deleted
     * @throws Exception If the person type is not supported or if there are issues with deletion
     */
    public static void deletePerson(Person personToDelete) throws Exception {
        String fileName;
        if (personToDelete instanceof Author) {
            fileName = "authors.json";
        } else if (personToDelete instanceof Illustrator) {
            fileName = "illustrators.json";
        } else {
            throw new Exception("Type de personne non pris en charge");
        }

        File file = new File(BASE_PATH + fileName);
        deleteEntity(file, personToDelete.getId(), true);
    }

    /**
     * Determines the appropriate file for a book based on its type.
     * <p>
     * This method uses pattern matching to identify the book's concrete type
     * and returns the corresponding file.
     * </p>
     *
     * @param bookToDelete The book to find the file for
     * @return The File object representing the JSON file for this book type
     * @throws Exception If the book type is not supported or the file doesn't exist
     */
    private static File getFile(Book bookToDelete) throws Exception {
        String fileName = switch (bookToDelete) {
            case Novel ignored -> "novels.json";
            case Manga ignored -> "mangas.json";
            case Bd ignored -> "bds.json";
            case null, default -> throw new Exception("Type de livre non pris en charge");
        };

        File file = new File(BASE_PATH + fileName);
        if (!file.exists()) {
            throw new Exception("Fichier introuvable: " + fileName);
        }
        return file;
    }

    /**
     * Deletes a book from the system.
     * <p>
     * This method determines the appropriate file based on the book's type
     * and calls deleteEntity to remove it from the file.
     * </p>
     *
     * @param bookToDelete The book to be deleted
     * @throws Exception If the book type is not supported or if there are issues with deletion
     */
    public static void deleteBook(Book bookToDelete) throws Exception {
        File file = getFile(bookToDelete);
        deleteEntity(file, bookToDelete.getId(), false);
    }
}