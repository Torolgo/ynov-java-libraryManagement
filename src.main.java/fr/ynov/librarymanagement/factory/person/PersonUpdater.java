package fr.ynov.librarymanagement.factory.person;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;

import java.io.File;
import java.util.List;

import static fr.ynov.librarymanagement.factory.person.PersonReader.readExistingData;
import static fr.ynov.librarymanagement.factory.person.PersonWriter.writeToFile;

public class PersonUpdater {
    private static final String BASE_PATH = "src.main.java/fr/ynov/librarymanagement/assets/";
    private static final Gson gson = new Gson();

    /**
     * Updates the biography of an author in the JSON file.
     *
     * @param id           The ID of the author to update.
     * @param newBiography The new biography to set.
     */
    public static void updateAuthorBiography(int id, String newBiography) {
        File file = new File(BASE_PATH + "authors.json");
        List<Author> authorJson = readExistingData(file, new TypeToken<List<Author>>() {}.getType());

        if (authorJson == null) {
            return;
        }

        boolean found = false;
        for (Author author : authorJson) {
            if (author.getId() == id) {
                author.setBiography(newBiography);
                found = true;
                break;
            }
        }

        if (found) {
            try {
                writeToFile(file, authorJson);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates the biography of an illustrator in the JSON file.
     *
     * @param id           The ID of the illustrator to update.
     * @param newBiography The new biography to set.
     */
    public static void updateIllustratorBiography(int id, String newBiography) {
        File file = new File(BASE_PATH + "illustrators.json");
        List<Illustrator> illustratorJson = readExistingData(file, new TypeToken<List<Illustrator>>() {}.getType());

        if (illustratorJson == null) {
            return;
        }

        boolean found = false;
        for (Illustrator illustrator : illustratorJson) {
            if (illustrator.getId() == id) {
                illustrator.setBiography(newBiography);
                found = true;
                break;
            }
        }

        if (found) {
            try {
                writeToFile(file, illustratorJson);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}