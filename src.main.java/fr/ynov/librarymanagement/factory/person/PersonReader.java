package fr.ynov.librarymanagement.factory.person;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Person;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class PersonReader {
    private static final String BASE_PATH = "src.main.java/fr/ynov/librarymanagement/assets/";
    private static final Gson gson = new Gson();

    /**
     * Reads the author file and populates the PersonFactory with authors.
     */
    public static void readAuthorFile() {
        List<Person> authorList;
        try {
            File file = new File(BASE_PATH + "authors.json");
            if (isValidFile(file)) {
                authorList = gson.fromJson(new FileReader(file),
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
                illustratorList = gson.fromJson(new FileReader(file),
                        new TypeToken<List<Illustrator>>() {}.getType());
                if (illustratorList != null) {
                    PersonFactory.getPersonList().addAll(illustratorList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads existing data from a file and returns it as a list of the specified type.
     *
     * @param file The file to read from.
     * @param type The type of data to read.
     * @return The list of data read from the file, or null if the file is empty or an error occurs.
     */
    public static <T> T readExistingData(File file, java.lang.reflect.Type type) {
        try {
            if (file.exists() && file.length() > 0) {
                return gson.fromJson(new FileReader(file), type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if a file is valid (exists and is not empty).
     *
     * @param file The file to check.
     * @return True if the file is valid, false otherwise.
     */
    private static boolean isValidFile(File file) {
        return file.exists() && file.length() > 0;
    }
}