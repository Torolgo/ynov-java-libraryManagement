package fr.ynov.librarymanagement.factory.person;

import com.google.gson.reflect.TypeToken;
import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.factory.FilesManagement;

import java.io.File;
import java.util.List;

public class PersonUpdater extends FilesManagement {

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