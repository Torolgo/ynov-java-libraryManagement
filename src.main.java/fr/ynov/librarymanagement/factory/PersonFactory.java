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
    private static final ArrayList<Person> PersonList = new ArrayList<>();

    public static void clearPersonList() {
        PersonList.clear();
    }

    public static void ReadAuthorFile() {
        Gson gson = new Gson();
        List<Person> authorList;
        try {
            authorList = gson.fromJson(new FileReader("src.main.java/fr/ynov/librarymanagement/assets/authors.json"), new TypeToken<List<Author>>() {
            }.getType());
            if (authorList != null) {
                PersonList.addAll(authorList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ReadIllustratorFile() {
        Gson gson = new Gson();
        List<Person> illustratorList;
        try {
            illustratorList = gson.fromJson(new FileReader("src.main.java/fr/ynov/librarymanagement/assets/illustrators.json"), new TypeToken<List<Illustrator>>() {
            }.getType());
            if (illustratorList != null) {
                PersonList.addAll(illustratorList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void WriteAuthorFile(int id, String name, String surname, String nationality, String dateOfBirth, String Biography, String wrintingStyle) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/authors.json");
        List<Author> authorJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                authorJson = gson.fromJson(new FileReader(file), new TypeToken<List<Author>>() {
                }.getType());
            }
            if (authorJson == null) {
                authorJson = new ArrayList<>();
            }
            for (Author author : authorJson) {
                if (author.getId() == id) {
                    throw new IllegalArgumentException("This id already exists");
                }
            }
            authorJson.add(new Author(id, name, surname, nationality, dateOfBirth, Biography, wrintingStyle));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(authorJson));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void WriteIllustratorFile(int id, String name, String surname, String nationality, String dateOfBirth, String Biography, String illustrationStyle) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/illustrators.json");
        List<Illustrator> illustratorJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                illustratorJson = gson.fromJson(new FileReader(file), new TypeToken<List<Illustrator>>() {
                }.getType());
            }
            if (illustratorJson == null) {
                illustratorJson = new ArrayList<>();
            }
            for (Illustrator illustrator : illustratorJson) {
                if (illustrator.getId() == id) {
                    throw new IllegalArgumentException("This id already exists");
                }
            }
            illustratorJson.add(new Illustrator(id, name, surname, nationality, dateOfBirth, Biography, illustrationStyle));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(illustratorJson));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateAuthorBiography(int id, String newBiography) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/authors.json");
        List<Author> authorJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                authorJson = gson.fromJson(new FileReader(file), new TypeToken<List<Author>>() {
                }.getType());
            }
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
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(gson.toJson(authorJson));
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateIllustratorBiography(int id, String newBiography) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/illustrators.json");
        List<Illustrator> illustratorJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                illustratorJson = gson.fromJson(new FileReader(file), new TypeToken<List<Illustrator>>() {
                }.getType());
            }
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
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(gson.toJson(illustratorJson));
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Person> getPersonList() {
        return PersonList;
    }
}