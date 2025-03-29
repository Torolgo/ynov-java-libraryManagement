package fr.ynov.librarymanagement.factory;

import com.google.gson.reflect.TypeToken;
import fr.ynov.librarymanagement.domain.Person;
import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Book;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Writer extends FilesManagement {

    /**
     * Writes a person's data (Author or Illustrator) to the appropriate file.
     * <p>
     * This method creates a new person with the information provided and saves it
     * in the corresponding JSON file. If the file already exists, the new person is
     * added to the existing list.
     * </p>
     *
     * @param name The person's first name
     * @param surname The person's surname.
     * @param nationality The person's nationality
     * @param dateOfBirth Person's date of birth
     * @param biography Person's biography
     * @param style The style (writing for an author or illustration for an illustrator)
     * @param personClass The person's class (Author.class or Illustrator.class)
     * @param filename The name of the file in which to save the data
     */
    public static <T extends Person> void writePersonFile(String name, String surname, String nationality,
                                                          String dateOfBirth, String biography, String style,
                                                          Class<T> personClass, String filename) {
        try {
            File file = new File(BASE_PATH + filename);
            List<T> personList = new ArrayList<>();

            if (isValidFile(file)) {
                List<T> existingPersons = readExistingData(file, TypeToken.getParameterized(List.class, personClass).getType());
                if (existingPersons != null) {
                    personList.addAll(existingPersons);
                }
            }

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
            writeToFile(file, personList);

            // Recharger la liste de personnes pour la mettre à jour en mémoire
            Reader.loadAllPersons();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a book's data (Novel, Manga, or Bd) to the appropriate file.
     * <p>
     * This method creates a new book with the information provided and saves it
     * in the corresponding JSON file. If the file already exists, the new book is
     * added to the existing list.
     * </p>
     *
     * @param bookSupplier A supplier that provides a new book instance
     * @param filename The name of the file in which to save the data
     * @param bookClass The class of the book (Novel.class, Manga.class, or Bd.class)
     */
    public static <T extends Book> void writeBookFile(Supplier<T> bookSupplier, String filename, Class<T> bookClass) {
        try {
            File file = new File(BASE_PATH + filename);
            List<T> bookList = new ArrayList<>();

            if (isValidFile(file)) {
                List<T> existingBooks = readExistingData(file, TypeToken.getParameterized(List.class, bookClass).getType());
                if (existingBooks != null) {
                    bookList.addAll(existingBooks);
                }
            }

            T newBook = bookSupplier.get();
            bookList.add(newBook);
            writeToFile(file, bookList);

            // Recharger la liste de livres pour la mettre à jour en mémoire
            Reader.loadAllBooks();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}