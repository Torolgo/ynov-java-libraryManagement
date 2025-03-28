package fr.ynov.librarymanagement.factory.person;

import com.google.gson.reflect.TypeToken;
import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Person;
import fr.ynov.librarymanagement.factory.FilesManagement;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class PersonReader extends FilesManagement {
    /**
     * Reads the author file and populates the PersonFactory with authors.
     */
    public static void readAuthorFile() {
        List<Person> authorList;
        try {
            File file = new File(BASE_PATH + "authors.json");
            if (isValidFile(file)) {
                authorList = GSON.fromJson(new FileReader(file),
                        new TypeToken<List<Author>>() {}.getType());
                if (authorList != null) {
                    PersonFactory.getPersonList().addAll(authorList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the illustrator file and populates the PersonFactory with illustrators.
     */
    public static void readIllustratorFile() {
        List<Person> illustratorList;
        try {
            File file = new File(BASE_PATH + "illustrators.json");
            if (isValidFile(file)) {
                illustratorList = GSON.fromJson(new FileReader(file),
                        new TypeToken<List<Illustrator>>() {}.getType());
                if (illustratorList != null) {
                    PersonFactory.getPersonList().addAll(illustratorList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}