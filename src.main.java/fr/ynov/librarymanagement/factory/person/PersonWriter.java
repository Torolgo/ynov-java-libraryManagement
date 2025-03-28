package fr.ynov.librarymanagement.factory.person;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static fr.ynov.librarymanagement.factory.person.PersonReader.readExistingData;

public class PersonWriter {
    private static final String BASE_PATH = "src.main.java/fr/ynov/librarymanagement/assets/";
    private static final Gson gson = new Gson();

    /**
     * Writes a new author's information to the authors JSON file.
     * <p>
     * This method creates a new Author object with the provided information and adds it
     * to the authors JSON file. If the file doesn't exist yet or is empty, a new list
     * is created. A unique ID is automatically assigned to the author from the next
     * available ID.
     * </p>
     *
     * @param name          The first name of the author
     * @param surname       The last name of the author
     * @param nationality   The nationality of the author
     * @param dateOfBirth   The date of birth of the author (as a string)
     * @param biography     The biography of the author
     * @param writingStyle  The writing style of the author
     */
    public static void writeAuthorFile(String name, String surname, String nationality,
                                       String dateOfBirth, String biography, String writingStyle) {
        int nextId = PersonFactory.getNextAvailablePersonId();
        File file = new File(BASE_PATH + "authors.json");
        List<Author> authorJson = readExistingData(file, new TypeToken<List<Author>>() {}.getType());

        if (authorJson == null) {
            authorJson = new ArrayList<>();
        }

        authorJson.add(new Author(nextId, name, surname, nationality, dateOfBirth, biography, writingStyle));

        try {
            writeToFile(file, authorJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a new illustrator's information to the illustrators JSON file.
     * <p>
     * This method creates a new Illustrator object with the provided information and adds it
     * to the illustrators JSON file. If the file doesn't exist yet or is empty, a new list
     * is created. A unique ID is automatically assigned to the illustrator from the next
     * available ID.
     * </p>
     *
     * @param name               The first name of the illustrator
     * @param surname            The last name of the illustrator
     * @param nationality        The nationality of the illustrator
     * @param dateOfBirth        The date of birth of the illustrator (as a string)
     * @param biography          The biography of the illustrator
     * @param illustrationStyle  The illustration style of the illustrator
     */
    public static void writeIllustratorFile(String name, String surname, String nationality,
                                            String dateOfBirth, String biography, String illustrationStyle) {
        int nextId = PersonFactory.getNextAvailablePersonId();
        File file = new File(BASE_PATH + "illustrators.json");
        List<Illustrator> illustratorJson = readExistingData(file, new TypeToken<List<Illustrator>>() {}.getType());

        if (illustratorJson == null) {
            illustratorJson = new ArrayList<>();
        }

        illustratorJson.add(new Illustrator(nextId, name, surname, nationality, dateOfBirth, biography, illustrationStyle));

        try {
            writeToFile(file, illustratorJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the given data to a file in JSON format.
     *
     * @param file The file to write to.
     * @param data The data to write.
     * @param <T>  The type of the data.
     * @throws Exception If an error occurs while writing to the file.
     */
    public static <T> void writeToFile(File file, T data) throws Exception {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(gson.toJson(data));
        }
    }
}