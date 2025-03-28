package fr.ynov.librarymanagement.factory;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FilesManagement {
    protected static final String BASE_PATH = "src.main.java/fr/ynov/librarymanagement/assets/";
    protected static final Gson GSON = new Gson();

    /**
     * Reads existing data from a JSON file and returns it as an object of the specified type.
     * <p>
     * This method uses Gson to deserialize the JSON data into the specified type.
     * </p>
     *
     * @param <T> The type of the object to be read
     * @param file The file to read from
     * @param type The type of the object to be read
     * @return The deserialized object, or null if the file is invalid or an error occurs
     */
    protected static <T> T readExistingData(File file, java.lang.reflect.Type type) {
        try {
            if (isValidFile(file)) {
                return GSON.fromJson(new FileReader(file), type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Writes data to a JSON file.
     * <p>
     * This method uses Gson to serialize the provided data into JSON format and writes it to the specified file.
     * </p>
     *
     * @param <T> The type of the object to be written
     * @param file The file to write to
     * @param data The data to be written
     * @throws Exception If an error occurs during writing
     */
    protected static <T> void writeToFile(File file, T data) throws Exception {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(GSON.toJson(data));
        }
    }

    /**
     * Checks if a file is valid (exists and is not empty).
     *
     * @param file The file to check.
     * @return True if the file is valid, false otherwise.
     */
    protected static boolean isValidFile(File file) {
        return file.exists() && file.length() > 0;
    }
}
