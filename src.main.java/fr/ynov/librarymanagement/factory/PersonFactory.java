package fr.ynov.librarymanagement.factory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.ynov.librarymanagement.domain.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class PersonFactory {
    private static ArrayList<Person> PersonList = new ArrayList<Person>();

    public static void ReadAuthorFile() {
        Gson gson = new Gson();
        List<Person> authorList = null;
        try {
            authorList = gson.fromJson(new FileReader("src.main.java/fr/ynov/librarymanagement/assets/authors.json"), new TypeToken<List<Author>>() {
            }.getType());
            PersonList.addAll(authorList);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public static void ReadIllustratorFile() {
        Gson gson = new Gson();
        List<Person> illustratorList = null;
        try {
            illustratorList = gson.fromJson(new FileReader("src.main.java/fr/ynov/librarymanagement/assets/illustrators.json"), new TypeToken<List<Illustrator>>() {
            }.getType());
            PersonList.addAll(illustratorList);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void WriteAuthorFile(int id, String name, String surname, String nationality, String dateOfBirth, String Biography, String wrintingStyle) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/authors.json");
        List<Author> authorJson = null;
        try {
            if (file.exists() && file.length() > 0) {
                authorJson = gson.fromJson(new FileReader(file), new TypeToken<List<Author>>() {
                }.getType());
            }
            assert authorJson != null;
            authorJson.forEach(author -> {
                if (author.getId() == id) {
                    throw new IllegalArgumentException("This id already exists");
                }
            });
            authorJson.add(new Author(id, name, surname, nationality, dateOfBirth, Biography, wrintingStyle));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(authorJson));
            fileWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void WriteIllustratorFile(int id, String name, String surname, String nationality, String dateOfBirth, String Biography, String illustrationStyle) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/illustrators.json");
        List<Illustrator> illustratorJson = null;
        try {
            if (file.exists() && file.length() > 0) {
                illustratorJson = gson.fromJson(new FileReader(file), new TypeToken<List<Illustrator>>() {
                }.getType());
            }
            assert illustratorJson != null;
            illustratorJson.forEach(illustrator -> {
                if (illustrator.getId() == id) {
                    throw new IllegalArgumentException("This id already exists");
                }
            });
            illustratorJson.add(new Illustrator(id, name, surname, nationality, dateOfBirth, Biography, illustrationStyle));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(illustratorJson));
            fileWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
