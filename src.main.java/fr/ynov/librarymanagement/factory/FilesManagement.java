package fr.ynov.librarymanagement.factory;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FilesManagement {
    protected static final String BASE_PATH = "src.main.java/fr/ynov/librarymanagement/assets/";
    protected static final Gson GSON = new Gson();

    /**
     * Reads existing data from a file and returns it as a list of the specified type.
     *
     * @param file The file to read from.
     * @param type The type of data to read.
     * @return The list of data read from the file, or null if the file is empty or an error occurs.
     */
    public static <T> T readExistingData(File file, java.lang.reflect.Type type) {
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
     * Writes the given data to a file in JSON format.
     *
     * @param file The file to write to.
     * @param data The data to write.
     * @param <T>  The type of the data.
     * @throws Exception If an error occurs while writing to the file.
     */
    public static <T> void writeToFile(File file, T data) throws Exception {
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
