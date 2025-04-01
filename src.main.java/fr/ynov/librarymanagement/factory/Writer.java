package fr.ynov.librarymanagement.factory;

import com.google.gson.reflect.TypeToken;
import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * The Writer class is responsible for writing data to JSON files.
 * It handles the creation of new authors, illustrators, and books,
 * ensuring that the data is saved in the correct format and location.
 */
public class Writer extends FilesManagement {

    /**
     * Loads existing data from a JSON file into a list.
     *
     * @param <T> The type of objects to load
     * @param filename The name of the file to load data from
     * @param dataClass The class of the objects to load
     * @return A list containing the loaded objects, or an empty list if the file doesn't exist or is invalid
     */
    private static <T> List<T> loadExistingData(String filename, Class<T> dataClass) {
        File file = new File(BASE_PATH + filename);
        List<T> dataList = new ArrayList<>();

        if (isValidFile(file)) {
            List<T> existingData = readExistingData(file, TypeToken.getParameterized(List.class, dataClass).getType());
            if (existingData != null) {
                dataList.addAll(existingData);
            }
        }

        return dataList;
    }

    /**
     * Writes a person's data (Author or Illustrator) to the appropriate file.
     * This method creates a new person with the information provided and saves it
     * in the corresponding JSON file.
     *
     * @param name The person's first name
     * @param surname The person's surname.
     * @param nationality The person's nationality
     * @param dateOfBirth Person's date of birth
     * @param biography Person's biography
     * @param style The style (writing for an author or illustration for an illustrator)
     * @param personClass The person's class (Author.class or Illustrator.Class)
     * @param filename The name of the file in which to save the data
     */
    public static <T extends Person> void writePersonFile(String name, String surname, String nationality,
                                                          String dateOfBirth, String biography, String style,
                                                          Class<T> personClass, String filename) {
        try {
            List<T> personList = loadExistingData(filename, personClass);

            int newId = PersonFactory.getNextAvailablePersonId();
            T newPerson;

            if (personClass == Author.class) {
                newPerson = personClass.cast(new Author(newId, name, surname, nationality, dateOfBirth, biography, style));
            } else if (personClass == Illustrator.class) {
                newPerson = personClass.cast(new Illustrator(newId, name, surname, nationality, dateOfBirth, biography, style));
            } else {
                throw new IllegalArgumentException("Type de personne non pris en charge");
            }

            personList.add(newPerson);
            writeToFile(new File(BASE_PATH + filename), personList);
            Reader.loadAllPersons();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a book's data (Novel, Manga, or Bd) to the appropriate file.
     * This method creates a new book with the information provided and saves it
     * in the corresponding JSON file.
     *
     * @param bookSupplier A supplier that provides a new book instance
     * @param filename The name of the file in which to save the data
     * @param bookClass The class of the book (Novel.class, Manga.class, or Bd.class)
     */
    public static <T extends Book> void writeBookFile(Supplier<T> bookSupplier, String filename, Class<T> bookClass) {
        try {
            List<T> bookList = loadExistingData(filename, bookClass);

            T newBook = bookSupplier.get();
            bookList.add(newBook);
            writeToFile(new File(BASE_PATH + filename), bookList);

            Reader.loadAllBooks();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}